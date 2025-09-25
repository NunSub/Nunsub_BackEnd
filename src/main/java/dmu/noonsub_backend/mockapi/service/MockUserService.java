package dmu.noonsub_backend.mockapi.service;

import dmu.noonsub_backend.domain.openbanking.dto.*;
import dmu.noonsub_backend.mockapi.dto.*;
import dmu.noonsub_backend.mockapi.model.MockUser;
import dmu.noonsub_backend.mockapi.repository.MockUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MockUserService {

    private final MockUserRepository userRepository;

    public MockUserService(MockUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public OpenBankingUserInfoResponseDto getUserInfo(String userSeqNo) {
        // 로그 추가
        log.info(">> [START] MockUserService.getUserInfo - Attempting to find user with UserSeqNo: {}",
                userSeqNo);

        // DB에서 사용자 조회
        Optional<MockUser> userOptional = userRepository.findByUserSeqNo(userSeqNo);

        if (userOptional.isPresent()) {
            // 사용자를 찾았을 경우
            log.info("++ User found in DB. Proceeding to map DTO. User: {}", userOptional.get().getUserName());
            return mapToUserInfoResponseDto(userOptional.get());
        } else {
            // 사용자를 찾지 못했을 경우
            log.warn("!! User NOT found in DB. Building 'User Not Found' response.");
            return buildUserNotFoundResponse();
        }
    }

    private OpenBankingUserInfoResponseDto mapToUserInfoResponseDto(MockUser user) {
        log.info(">> [EXEC] Mapping user '{}' to DTO", user.getUserName());
        OpenBankingUserInfoResponseDto responseDto = new OpenBankingUserInfoResponseDto(
                "MOCK_API_TRAN_ID_" + System.currentTimeMillis(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSS")),
                "A0000",
                "정상 처리",
                user.getUserSeqNo(),
                user.getCi(),
                user.getUserName(),
                user.getUserInfo(),
                user.getUserGender(),
                user.getUserCellNo(),
                user.getUserEmail(),
                String.valueOf(user.getAccounts().size()),
                user.getAccounts().stream().map(account ->
                        AccountDetailDto.builder()
                                .fintechUseNum(account.getFintechUseNum())
                                .accountAlias(account.getAccountAlias())
                                .bankCodeStd(account.getBank().getBankCodeStd())
                                .bankName(account.getBank().getBankName())
                                .accountNumMasked(account.getAccountNumMasked())
                                .accountHolderName(account.getAccountHolderName())
                                .accountHolderType(account.getAccountHolderType())
                                .accountType(account.getAccountType())
                                .inquiryAgreeYn(account.getInquiryAgreeYn())
                                .transferAgreeYn(account.getTransferAgreeYn())
                                .payerNum("12345") // 예시 payerNum
                                .build()
                ).collect(Collectors.toList()),
                String.valueOf(user.getCardInfos().size()),
                user.getCardInfos().stream().map(card ->
                        new CardInfoDetailDto(
                                card.getBankCodeStd(),
                                card.getMemberBankCode(),
                                card.getInquiryAgreeDtime()
                        )
                ).collect(Collectors.toList()),
                String.valueOf(user.getPayInfos().size()),
                user.getPayInfos().stream().map(pay ->
                        new PayInfoDetailDto(
                                pay.getBankCodeStd(),
                                pay.getInquiryAgreeDtime()
                        )
                ).collect(Collectors.toList()),
                String.valueOf(user.getInsuranceInfos().size()),
                user.getInsuranceInfos().stream().map(insurance ->
                        new InsuranceInfoDetailDto(
                                insurance.getBankCodeStd(),
                                insurance.getInquiryAgreeDtime()
                        )
                ).collect(Collectors.toList()),
                String.valueOf(user.getLoanInfos().size()),
                user.getLoanInfos().stream().map(loan ->
                        new LoanInfoDetailDto(
                                loan.getBankCodeStd(),
                                loan.getInquiryAgreeDtime()
                        )
                ).collect(Collectors.toList())
        );
        log.info("Mapped MockUser to OpenBankingUserInfoResponseDto: {}", responseDto);
        return responseDto;
    }

    private OpenBankingUserInfoResponseDto buildUserNotFoundResponse() {
        log.info(">> [EXEC] Building 'User Not Found' response DTO.");
        return new OpenBankingUserInfoResponseDto(
                "MOCK_API_TRAN_ID_" + System.currentTimeMillis(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")),
                "A0313",
                "사용자 불일치",
                null, null, null, null, null, null, null,
                "0", Collections.emptyList(),
                "0", Collections.emptyList(),
                "0", Collections.emptyList(),
                "0", Collections.emptyList(),
                "0", Collections.emptyList()
        );
    }
}
