package dmu.noonsub_backend.domain.openbanking.service;

import dmu.noonsub_backend.domain.common.exception.CustomException;
import dmu.noonsub_backend.domain.common.exception.ErrorCode;
import dmu.noonsub_backend.domain.member.entity.Member;
import dmu.noonsub_backend.domain.member.service.MemberService;
import dmu.noonsub_backend.domain.openbanking.OpenBankingClient;
import dmu.noonsub_backend.domain.openbanking.OpenBankingProperties;
import dmu.noonsub_backend.domain.openbanking.dto.*;
import dmu.noonsub_backend.domain.openbanking.entity.MemberBank;
import dmu.noonsub_backend.domain.openbanking.repository.MemberBankRepository;
import dmu.noonsub_backend.global.util.CryptoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class OpenBankingService {

    private final MemberService memberService;
    private final MemberBankRepository memberBankRepository;
    private final OpenBankingProperties openBankingProperties;
    private final OpenBankingClient openBankingClient; // 실제 mTLS 통신을 담당할 클라이언트
    private final CryptoUtil cryptoUtil; // 토큰 암호화를 담당할 유틸리티
    private final RedisTemplate<String, String> redisTemplate; // state, code_verifier 저장용 (추후 구현)

    public String getAuthorizationUrl(String phoneNumber, AuthorizeRequestDto requestDto) {
        // 1. CSRF 방어를 위한 state 생성
        String state = generateRandomString();

        // 2. (임시 저장) state와 code_verifier를 Reids 등에 사용자 정보와 함계 잠시 저장 (유효시간 5분)
        redisTemplate.opsForValue().set(state, phoneNumber, Duration.ofMinutes(5));

        return UriComponentsBuilder.fromHttpUrl(openBankingProperties.getAuthorizeUrl())
                .queryParam("response_type", "code")
                .queryParam("client_id", openBankingProperties.getClientId())
                .queryParam("redirect_uri", openBankingProperties.getRedirectUri())
                .queryParam("scope", "login inquiry transfer")
                .queryParam("state", state)
                .queryParam("auth_type", "0")
                .queryParam("code_challenge", requestDto.getCode_challenge())
                .queryParam("code_challenge_method", "S256")
                .encode()// 회원 식별을 위한 전화번호 전달
                .toUriString();
    }

    @Transactional
    public void exchangeTokenAndSave(String phoneNumber, TokenExchangeRequestDto requestDto) {
        String savedPhonNumber = redisTemplate.opsForValue().get(requestDto.getState());

        if (savedPhonNumber == null || !savedPhonNumber.equals(phoneNumber)) {
            throw new SecurityException("Invalid or expired state parameter. CSRF attack detected.");
        }

        redisTemplate.delete(requestDto.getState());

        try {
            Member member = memberService.getMemberByPhoneNumber(phoneNumber);

            // 2. (mTLS 통신) OpenBankingClient를 통해 토큰 발급 요청
            OpenBankingTokenResponseDto tokenResponse = openBankingClient.exchangeToken(requestDto);

            // 발급된 토큰으로 사용자 정보 및 계좌 목록 조회
            OpenBankingUserInfoResponseDto userInfo = openBankingClient.getUserInfo(
                    tokenResponse.getAccessToken(), tokenResponse.getUserSeqNo()
            );

            // 3. 조회된 계좌 목록에서 첫 번째 계좌를 대표로 등록 (실제 서비스에서는 사용자가 선택)
            if (userInfo.getResList() == null || userInfo.getResList().isEmpty()) {
                throw new RuntimeException("No accounts found for user");
            }
            OpenBankingUserInfoResponseDto.AccountInfo primaryAccount = userInfo.getResList().get(0);
            // -- 통신 결과 ---

            // 3. 토큰 암호화
            String encryptedAccessToken = cryptoUtil.encrypt(tokenResponse.getAccessToken());
            String encryptedRefreshToken = cryptoUtil.encrypt(tokenResponse.getRefreshToken());


            // 4. DB에 저장
            MemberBank memberBank = MemberBank.builder()
                    .member(member)
                    .bankCode(primaryAccount.getBankCodeStd()) // 신한은행 코드 예시
                    .fintechUseNum(primaryAccount.getFintechUseNum())
                    .userSeqNo(tokenResponse.getUserSeqNo())
                    .accessTokenEnc(encryptedAccessToken)
                    .refreshTokenEnc(encryptedRefreshToken)
                    .accessTokenExpiresAt(java.time.Instant.now().plusSeconds(tokenResponse.getExpiresIn()))
                    .build();
            memberBankRepository.save(memberBank);
        } catch (Exception e) {
            throw new RuntimeException("Failed to process token exchange and save data", e);
        }

    }

    public String generateRandomString() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    @Transactional
    public TransactionHistoryDto.Response getTransactionHistory(
            String phoneNumber, String fintechUseNum, String fromDate, String toDate, String sortOrder
    ) {
        try {
            Member member = memberService.getMemberByPhoneNumber(phoneNumber);
            MemberBank memberBank = memberBankRepository.findByMemberAndFintechUseNum(member, fintechUseNum)
                    .orElseThrow(() -> new IllegalArgumentException("Bank account not foud or not owned by th user."));

            if (Instant.now().isAfter(memberBank.getAccessTokenExpiresAt())) {
                memberBank = refreshTokenAndUpdate(memberBank);
            }

            String accessToken = cryptoUtil.decrypt(memberBank.getAccessTokenEnc());

            TransactionHistoryDto.OpenBankingResponse apiResponse = openBankingClient.getTransactionHistory(accessToken,
                    fintechUseNum, fromDate, toDate, sortOrder);

            return new TransactionHistoryDto.Response(
                    apiResponse.getBankName(),
                    apiResponse.getAccountAlias(),
                    apiResponse.getAccountNumMasked(),
                    apiResponse.getResList()
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to get transaction history", e);
        }
    }

    private MemberBank refreshTokenAndUpdate(MemberBank memberBank) throws Exception {
        String refreshToken = cryptoUtil.decrypt(memberBank.getRefreshTokenEnc());

        OpenBankingTokenResponseDto tokenResponse = openBankingClient.refreshToken(refreshToken);

        String encryptedAccessToken = cryptoUtil.encrypt(tokenResponse.getAccessToken());
        String encryptedRefreshToken = tokenResponse.getRefreshToken() != null ?
                cryptoUtil.encrypt(tokenResponse.getRefreshToken()) : memberBank.getRefreshTokenEnc();

        memberBank.updateTokens(
                encryptedAccessToken,
                encryptedRefreshToken,
                Instant.now().plusSeconds(tokenResponse.getExpiresIn())
        );
        return memberBankRepository.save(memberBank);
    }

    @Transactional(readOnly = true)
    public List<AccountSummaryDto> getAccountList(String phoneNumber) {
        Member member = memberService.getMemberByPhoneNumber(phoneNumber);
        List<MemberBank> memberBanks = memberBankRepository.findAllByMember(member);

        return memberBanks.stream()
                .map(AccountSummaryDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void unlinkAccount(String phoneNumber, String fintechUseNum) {
        Member member = memberService.getMemberByPhoneNumber(phoneNumber);
        MemberBank memberBank = memberBankRepository.findByMemberAndFintechUseNum(member, fintechUseNum)
                .orElseThrow(() -> new CustomException(ErrorCode.BANK_ACCOUNT_NOT_FOUND));

        memberBankRepository.delete(memberBank);
    }


}
