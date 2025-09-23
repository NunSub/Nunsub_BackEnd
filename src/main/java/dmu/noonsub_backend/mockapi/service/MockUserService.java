package dmu.noonsub_backend.mockapi.service;

import dmu.noonsub_backend.mockapi.dto.*;
import dmu.noonsub_backend.mockapi.model.MockUser;
import dmu.noonsub_backend.mockapi.repository.MockUserRepository;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Service
public class MockUserService {

    private final MockUserRepository userRepository;

    public MockUserService(MockUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public MockUserInfoResponseDto getUserInfo(String userSeqNo) {
        return userRepository.findByUserSeqNo(userSeqNo)
                .map(this::mapToUserInfoResponseDto)
                .orElse(buildUserNotFoundResponse());
    }

    private MockUserInfoResponseDto mapToUserInfoResponseDto(MockUser user) {
        return MockUserInfoResponseDto.builder()
                .api_tran_id("MOCK_API_TRAN_ID_" + System.currentTimeMillis())
                .api_tran_dtm(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")))
                .rsp_code("A0000")
                .rsp_message("정상 처리")
                .user_seq_no(user.getUserSeqNo())
                .user_ci(user.getCi())
                .user_name(user.getUserName())
                .res_cnt(String.valueOf(user.getAccounts().size()))
                .res_list(user.getAccounts().stream().map(account ->
                        MockAccountDetailDto.builder()
                                .fintech_use_num(account.getFintechUseNum())
                                .account_alias(account.getAccountAlias())
                                .bank_code_std(account.getBank().getBankCodeStd())
                                .bank_name(account.getBank().getBankName())
                                .account_num_masked(account.getAccountNumMasked())
                                .account_holder_name(account.getAccountHolderName())
                                .account_type(account.getAccountType())
                                .inquiry_agree_yn(account.getInquiryAgreeYn())
                                .transfer_agree_yn(account.getTransferAgreeYn())
                                .build()
                ).collect(Collectors.toList()))
                .inquiry_card_cnt(String.valueOf(user.getCardInfos().size()))
                .inquiry_card_list(user.getCardInfos().stream().map(card ->
                        MockCardInfoDetailDto.builder()
                                .bank_code_std(card.getBankCodeStd())
                                .member_bank_code(card.getMemberBankCode())
                                .inquiry_agree_dtime(card.getInquiryAgreeDtime())
                                .build()
                ).collect(Collectors.toList()))
                // --- 👇 Pay, Insurance, Loan 정보 매핑 추가 ---
                .inquiry_pay_cnt(String.valueOf(user.getPayInfos().size()))
                .inquiry_pay_list(user.getPayInfos().stream().map(pay ->
                        MockPayInfoDetailDto.builder()
                                .bank_code_std(pay.getBankCodeStd())
                                .inquiry_agree_dtime(pay.getInquiryAgreeDtime())
                                .build()
                ).collect(Collectors.toList()))
                .inquiry_insurance_cnt(String.valueOf(user.getInsuranceInfos().size()))
                .inquiry_insurance_list(user.getInsuranceInfos().stream().map(insurance ->
                        MockInsuranceInfoDetailDto.builder()
                                .bank_code_std(insurance.getBankCodeStd())
                                .inquiry_agree_dtime(insurance.getInquiryAgreeDtime())
                                .build()
                ).collect(Collectors.toList()))
                .inquiry_loan_cnt(String.valueOf(user.getLoanInfos().size()))
                .inquiry_loan_list(user.getLoanInfos().stream().map(loan ->
                        MockLoanInfoDetailDto.builder()
                                .bank_code_std(loan.getBankCodeStd())
                                .inquiry_agree_dtime(loan.getInquiryAgreeDtime())
                                .build()
                ).collect(Collectors.toList()))
                .build();
    }

    private MockUserInfoResponseDto buildUserNotFoundResponse() {
        return MockUserInfoResponseDto.builder()
                .api_tran_id("MOCK_API_TRAN_ID_" + System.currentTimeMillis())
                .api_tran_dtm(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")))
                .rsp_code("A0313")
                .rsp_message("사용자 불일치")
                .build();
    }
}
