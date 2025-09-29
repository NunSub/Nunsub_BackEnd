package dmu.noonsub_backend.domain.openbanking.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dmu.noonsub_backend.domain.common.exception.CustomException;
import dmu.noonsub_backend.domain.common.exception.ErrorCode;
import dmu.noonsub_backend.domain.member.entity.*;
import dmu.noonsub_backend.domain.member.repository.*;
import dmu.noonsub_backend.domain.member.service.MemberService;
import dmu.noonsub_backend.domain.openbanking.OpenBankingClient;
import dmu.noonsub_backend.domain.openbanking.OpenBankingProperties;
import dmu.noonsub_backend.domain.openbanking.dto.*;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class OpenBankingService {

    private final MemberService memberService;
    private final MemberBankRepository memberBankRepository;
    private final MemberCardRepository memberCardRepository;
    private final MemberPayRepository memberPayRepository;
    private final MemberInsuranceRepository memberInsuranceRepository;
    private final MemberLoanRepository memberLoanRepository;
    private final TransactionRepository transactionRepository;
    private final OpenBankingProperties openBankingProperties;
    private final OpenBankingClient openBankingClient; // 실제 mTLS 통신을 담당할 클라이언트
    private final CryptoUtil cryptoUtil; // 토큰 암호화를 담당할 유틸리티
    private final RedisTemplate<String, String> redisTemplate; // state, code_verifier 저장용 (추후 구현)
    private final ObjectMapper objectMapper;

    public String getAuthorizationUrl(String phoneNumber) {
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
                .queryParam("user_cell_no", phoneNumber)
                .encode()// 회원 식별을 위한 전화번호 전달
                .toUriString();
    }

    @Transactional
    public OpenBankingTokenResponseDto exchangeTokenAndLinkMember(
            String phoneNumber,
            TokenExchangeRequestDto requestDto) {
        log.info(">>>> DB에서 다음 전화번호로 회원을 조회합니다: '{}'", phoneNumber);
        // 1. state 검증
        String savedPhoneNumber = redisTemplate.opsForValue().get(requestDto.getState());
        if (savedPhoneNumber == null || !savedPhoneNumber.equals(phoneNumber)) {
            throw new SecurityException("Invalid or expired state parameter.");
        }
        redisTemplate.delete(requestDto.getState());
        try {
            // 1. 오픈뱅킹 API를 통해 토큰 발급 요청
            OpenBankingTokenResponseDto tokenResponse = openBankingClient.exchangeToken(requestDto);
            String userSeqNo = tokenResponse.getUserSeqNo();

            // 2. 토큰 암호화
            String encryptedAccessToken = cryptoUtil.encrypt(tokenResponse.getAccessToken());
            String encryptedRefreshToken = cryptoUtil.encrypt(tokenResponse.getRefreshToken());

            // 3. Redis에 저장할 데이터 준비 (Key: user_seq_no, Value: 토큰 Map)
            String redisKey = "openbanking:token:" + tokenResponse.getUserSeqNo();
            Map<String, String> tokenData = new HashMap<>();
            tokenData.put("accessToken", encryptedAccessToken);
            tokenData.put("refreshToken", encryptedRefreshToken);

            // 4. Redis에 토큰 저장 및 만료 시간(TTL) 설정
            redisTemplate.opsForValue().set(
                    redisKey,
                    objectMapper.writeValueAsString(tokenData), // Map -> JSON 문자열
                    Duration.ofSeconds(tokenResponse.getExpiresIn())
            );

            log.info("Token saved to Redis for user_seq_no: {}", tokenResponse.getUserSeqNo());

            Member member = memberService.getMemberByPhoneNumber(phoneNumber);
            member.updateOpenBankingUserSeqNo(userSeqNo);

            return tokenResponse;
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize token data for Redis", e);
            throw new RuntimeException("Redis 데이터 직렬화에 실패했습니다.", e);
        } catch (Exception e) {
            log.error("Failed to exchange token and save to Redis", e);
            throw new RuntimeException("토큰 발급 및 Redis 저장에 실패했습니다.", e);
        }
    }

    @Transactional
    public OpenBankingUserInfoResponseDto syncOpenBankingInfo(String phoneNumber) {
        log.info(">> [START] OpenBankingService.syncOpenBankingInfo - User: {}", phoneNumber);
        // 1. 전화번호로 Member와 userSeqNo 조회
        Member member = memberService.getMemberByPhoneNumber(phoneNumber);
        String userSeqNo = member.getUserSeqNo();
        if (userSeqNo == null) {
            log.error("!! User '{}' has no userSeqNo. Cannot sync open banking info.", phoneNumber);
            throw new CustomException(ErrorCode.NO_DATA_FOUND);
        }

        String accessToken = getValidAccessToken(userSeqNo);

        try {
            OpenBankingUserInfoResponseDto userInfo = openBankingClient.getUserInfo(accessToken, userSeqNo);

            // 1. Member 엔티티에 사용자 정보(CI, 이메일, 성별) 업데이트
            member.updateOpenBankingUserInfo(userInfo);

            // 2. 기존 금융 정보 전체 삭제
            deleteAllFinancialInfo(member);

            // 3. 각 금융 정보별로 저장 메소드 호출
            saveAccountsAndTransactions(member, userInfo, accessToken);
            saveCards(member, userInfo);
            savePays(member, userInfo);
            saveInsurances(member, userInfo);
            saveLoans(member, userInfo);

            return userInfo;

        } catch (Exception e) {
            log.error("Failed to sync open banking info for user: {}", phoneNumber, e);
            throw new RuntimeException("오픈뱅킹 정보 동기화에 실패했습니다.", e);
        }
    }

    private void deleteAllFinancialInfo(Member member) {
        // 계좌 삭제 전, 각 계좌에 연결된 거래내역부터 삭제
        List<MemberAccounts> memberAccounts = memberBankRepository.findAllByMember(member);
        for (MemberAccounts account : memberAccounts) {
            transactionRepository.deleteAllByMemberAccount(account);
        }
        memberBankRepository.deleteAllByMember(member);

        // 나머지 금융 정보 삭제
        memberCardRepository.deleteAllByMember(member);
        memberPayRepository.deleteAllByMember(member);
        memberInsuranceRepository.deleteAllByMember(member);
        memberLoanRepository.deleteAllByMember(member);
        log.info("Deleted all previous financial info for user: {}", member.getCellNo());
    }

    private void saveAccountsAndTransactions(
            Member member, OpenBankingUserInfoResponseDto userInfo, String accessToken)
    {
        if (userInfo.getResList() == null) return;

        for (AccountDetailDto accountDto : userInfo.getResList()) {
            MemberAccounts savedAccount = memberBankRepository.save(MemberAccounts.builder()
                    .member(member)
                    .fintechUseNum(accountDto.getFintechUseNum())
                    .accountAlias(accountDto.getAccountAlias())
                    .bankCodeStd(accountDto.getBankCodeStd())
                    .bankCodeSub(accountDto.getBankCodeSub())
                    .bankName(accountDto.getBankName())
                    .accountNumMasked(accountDto.getAccountNumMasked())
                    .accountHolderName(accountDto.getAccountHolderName())
                    .accountHolderType(accountDto.getAccountHolderType())
                    .accountType(accountDto.getAccountType())
                    .inquiryAgreeYn(accountDto.getInquiryAgreeYn())
                    .inquiryAgreeDtime(accountDto.getInquiryAgreeDtime() != null ?
                            Instant.parse(accountDto.getInquiryAgreeDtime()) : Instant.now())
                    .transferAgreeYn(accountDto.getTransferAgreeYn())
                    .transferAgreeDtime(accountDto.getTransferAgreeDtime() != null ?
                            Instant.parse(accountDto.getTransferAgreeDtime()) : Instant.now())
                    .build());

            syncAllTransactionsForAccount(savedAccount, accessToken);
        }
    }

    private void saveCards(Member member, OpenBankingUserInfoResponseDto userInfo) {
        if (userInfo.getInquiryCardList() == null) return;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        for (CardInfoDetailDto cardDto : userInfo.getInquiryCardList()) {
            memberCardRepository.save(MemberCard.builder()
                    .member(member)
                    .bankCodeStd(cardDto.getBankCodeStd())
                    .memberBankCode(cardDto.getMemberBankCode())
                    .inquiryAgreeDtime(cardDto.getInquiryAgreeDtime() != null ?
                            LocalDateTime.parse(cardDto.getInquiryAgreeDtime(), formatter) :
                            LocalDateTime.now())
                    .build());
        }
    }

    private void savePays(Member member, OpenBankingUserInfoResponseDto userInfo) {
        if (userInfo.getInquiryPayList() == null) return;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        for (PayInfoDetailDto payDto : userInfo.getInquiryPayList()) {
            memberPayRepository.save(MemberPay.builder()
                    .member(member)
                    .bankCodeStd(payDto.getBankCodeStd())
                    .inquiryAgreeDtime(payDto.getInquiryAgreeDtime() != null ?
                            LocalDateTime.parse(payDto.getInquiryAgreeDtime(), formatter) :
                            LocalDateTime.now())
                    .build());
        }
    }

    private void saveInsurances(Member member, OpenBankingUserInfoResponseDto userInfo) {
        if (userInfo.getInquiryInsuranceList() == null) return;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        for (InsuranceInfoDetailDto insuranceDto : userInfo.getInquiryInsuranceList()) {
            memberInsuranceRepository.save(MemberInsurance.builder()
                    .member(member)
                    .bankCodeStd(insuranceDto.getBankCodeStd())
                    .inquiryAgreeDtime(insuranceDto.getInquiryAgreeDtime() != null ?
                            LocalDateTime.parse(insuranceDto.getInquiryAgreeDtime(), formatter) :
                            LocalDateTime.now())
                    .build());
        }
    }

    private void saveLoans(Member member, OpenBankingUserInfoResponseDto userInfo) {
        if (userInfo.getInquiryLoanList() == null) return;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        for (LoanInfoDetailDto loanDto : userInfo.getInquiryLoanList()) {
            memberLoanRepository.save(MemberLoan.builder()
                    .member(member)
                    .bankCodeStd(loanDto.getBankCodeStd())
                    .inquiryAgreeDtime(loanDto.getInquiryAgreeDtime() != null ?
                            LocalDateTime.parse(loanDto.getInquiryAgreeDtime(), formatter) :
                            LocalDateTime.now())
                    .build());
        }
    }

    private String getValidAccessToken(String userSeqNo) {
        String redisKey = "openbanking:token:" + userSeqNo;
        String tokenJson = redisTemplate.opsForValue().get(redisKey);

        if (tokenJson == null) {
            // TODO: Redis에 토큰이 없는 경우 (만료), DB에서 Refresh Token을 찾아 갱신하는 로직 구현 필요
            log.warn("Access Token for user {} has expired or does not exist. Refresh is needed.", userSeqNo);
            throw new CustomException(ErrorCode.TOKEN_EXPIRED);
        }

        try {
            Map<String, String> tokenData = objectMapper.readValue(tokenJson, new TypeReference<>() {});
            return cryptoUtil.decrypt(tokenData.get("accessToken"));
        } catch (Exception e) {
            throw new RuntimeException("토큰 데이터를 파싱하거나 복호화하는 데 실패했습니다.", e);
        }
    }
    public String generateRandomString() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    @Transactional
    public void unlinkAccount(String phoneNumber, String fintechUseNum) {
        Member member = memberService.getMemberByPhoneNumber(phoneNumber);
        MemberAccounts memberAccounts = memberBankRepository.findByMemberAndFintechUseNum(member, fintechUseNum)
                .orElseThrow(() -> new CustomException(ErrorCode.BANK_ACCOUNT_NOT_FOUND));

        memberBankRepository.delete(memberAccounts);
    }

    private void syncAllTransactionsForAccount(MemberAccounts memberAccount, String accessToken) {
        log.info("Starting full transaction sync for fintechUseNum: {}",
                memberAccount.getFintechUseNum());

        transactionRepository.deleteAllByMemberAccount(memberAccount);
        log.info("Deleted existing transactions for fintechUseNum: {}", memberAccount.getFintechUseNum());

        String nextPageTraceInfo = null;
        String nextPageYn = "Y";
        int totalSavedCount = 0;

        LocalDate today = LocalDate.now();
        LocalDate threeMonthsAgo = today.minusMonths(3);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String fromDate = threeMonthsAgo.format(dateFormatter);
        String toDate = today.format(dateFormatter);

        while ("Y".equals(nextPageYn)) {
            log.info(">> [Loop Start] 페이지네이션 루프 시작. FintechUseNum: {}, TraceInfo: {}",
                    memberAccount.getFintechUseNum(), nextPageTraceInfo);

            try {
                log.info("--> [API Call] OpenBankingClient.getTransactionHistory 호출 시작...");
                TransactionDto.OpenBankingResponse apiResponse =
                        openBankingClient.getTransactionHistory(
                                accessToken,
                                memberAccount.getFintechUseNum(),
                                fromDate,
                                toDate,
                                "D", // 정렬 순서 (내림차순)
                                "A", // 조회 구분 (전체)
                                nextPageTraceInfo
                        );
                log.info("<-- [API Resp] OpenBankingClient.getTransactionHistory 응답 수신 완료.");

                if (apiResponse != null && apiResponse.getResList() != null &&
                        !apiResponse.getResList().isEmpty()) {

                    List<MemberTransaction> transactionsToSave = new ArrayList<>();
                    for (TransactionDto.Transaction txDto : apiResponse.getResList()) {
                        transactionsToSave.add(MemberTransaction.builder()
                                .memberAccount(memberAccount)
                                .tranDate(txDto.getTranDate())
                                .tranTime(txDto.getTranTime())
                                .inoutType(txDto.getInoutType())
                                .tranType(txDto.getTranType())
                                .printContent(txDto.getPrintContent())
                                .tranAmt(txDto.getTranAmt())
                                .afterBalanceAmt(txDto.getAfterBalanceAmt())
                                .branchName(txDto.getBranchName())
                                .build());
                    }
                    log.info("==> [DB Save] DB에 거래내역 {}건 저장 시작.", transactionsToSave.size());
                    saveInBatch(transactionsToSave);
                    totalSavedCount += apiResponse.getResList().size();
                    log.info("<== [DB Done] 이번 페이지 거래내역 {}건 저장 완료.", apiResponse.getResList().size());
                } else {
                    log.warn("!! API 응답이 비어있어 이번 페이지의 저장을 건너뜁니다.");
                }

                nextPageYn = (apiResponse != null && apiResponse.getNextPageYn() != null) ?
                        apiResponse.getNextPageYn() : "N";
                nextPageTraceInfo = (apiResponse != null) ? apiResponse.getBeforeInquiryTraceInfo() :
                        null;

                log.info("<< [Loop End] 다음 페이지 정보 업데이트: nextPageYn={}, nextPageTraceInfo={}",
                        nextPageYn, nextPageTraceInfo);

            } catch (Exception e) {
                log.error("!! 페이지 동기화 중 예외 발생. FintechUseNum: {}. 해당 계좌 동기화를 중단합니다.",
                        memberAccount.getFintechUseNum(), e);
                nextPageYn = "N"; // 에러 발생 시 해당 계좌의 동기화 중단
            }
        }
        log.info("Finished full transaction sync for fintechUseNum: {}. Total saved: {}",
                memberAccount.getFintechUseNum(), totalSavedCount);
    }

    private void saveInBatch(List<MemberTransaction> transactions) {
        final int batchSize = 500; // 한번에 저장할 묶음 크기
        for (int i = 0; i < transactions.size(); i += batchSize) {
            int end = Math.min(i + batchSize, transactions.size());
            List<MemberTransaction> batch = transactions.subList(i, end);
            transactionRepository.saveAll(batch);
        }
    }

    public String getPhoneNumberByState(String state) {
        return redisTemplate.opsForValue().get(state);
    }
}
