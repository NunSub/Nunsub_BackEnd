package dmu.noonsub_backend.mockapi.service;

import dmu.noonsub_backend.mockapi.dto.*;
import dmu.noonsub_backend.mockapi.model.MockAccount;
import dmu.noonsub_backend.mockapi.model.MockTransaction;
import dmu.noonsub_backend.mockapi.repository.MockAccountRepository;
import dmu.noonsub_backend.mockapi.repository.MockTransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // 추가

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MockTransferService {

    private final MockAccountRepository accountRepository; // 수정
    private final MockTransactionRepository transactionRepository;

    public MockTransferService(MockAccountRepository accountRepository, MockTransactionRepository transactionRepository) { // 수정
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional // DB 데이터 변경이 있으므로 트랜잭션 처리
    public WithdrawResponseDto processWithdrawal(WithdrawRequestDto dto) {
        Optional<MockAccount> accountOpt = accountRepository.findByFintechUseNum(dto.fintech_use_num());

        if (accountOpt.isEmpty()) {
            return WithdrawResponseDto.builder().rsp_code("A0304").rsp_message("핀테크이용번호 불일치").build();
        }

        MockAccount account = accountOpt.get();
        long requestAmount = Long.parseLong(dto.tran_amt());

        if (account.getBalance() < requestAmount) {
            return WithdrawResponseDto.builder().rsp_code("A0008").rsp_message("잔액 부족")
                    .wd_limit_remain_amt(String.valueOf(account.getBalance())).build();
        }

        // 잔액 차감
        account.setBalance(account.getBalance() - requestAmount);
        accountRepository.save(account); // 변경된 내용을 DB에 저장

        return WithdrawResponseDto.builder()
                .api_tran_id("MOCK_API_TRAN_ID_" + System.currentTimeMillis())
                .rsp_code("A0000").rsp_message("처리 성공")
                .fintech_use_num(dto.fintech_use_num())
                .tran_amt(dto.tran_amt())
                .wd_limit_remain_amt(String.valueOf(account.getBalance()))
                .build();
    }

    @Transactional(readOnly = true) // 읽기 전용 트랜잭션
    public MockBalanceResponseDto getBalance(String fintechUseNum) {
        return accountRepository.findByFintechUseNum(fintechUseNum)
                .map(account -> MockBalanceResponseDto.builder()
                        .api_tran_id("MOCK_API_TRAN_ID_" + System.currentTimeMillis())
                        .api_tran_dtm(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")))
                        .rsp_code("A0000").rsp_message("정상")
                        .balance_amt(String.valueOf(account.getBalance()))
                        .build())
                .orElse(MockBalanceResponseDto.builder()
                        .api_tran_id("MOCK_API_TRAN_ID_" + System.currentTimeMillis())
                        .api_tran_dtm(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")))
                        .rsp_code("A0304").rsp_message("핀테크이용번호 불일치")
                        .balance_amt("0")
                        .build());
    }

    @Transactional(readOnly = true)
    public MockTransactionResponseDto getTransactionHistory(String fintechUseNum, String fromDate, String toDate, String inquiryType) {
        Optional<MockAccount> accountOpt = accountRepository.findByFintechUseNum(fintechUseNum);

        if (accountOpt.isEmpty()) {
            return MockTransactionResponseDto.builder().rsp_code("A0304").rsp_message("핀테크이용번호 불일치").build();
        }

        MockAccount account = accountOpt.get();

        List<MockTransaction> transactions;

        // 조회구분코드(inquiryType)에 따른 분기 처리
        if ("A".equals(inquiryType)) { // 전체
            transactions = transactionRepository.findByAccountIdAndTranDateBetweenOrderByTranDateDescTranTimeDesc(account.getId(), fromDate, toDate);
        } else {
            String inoutType = "I".equals(inquiryType) ? "입금" : "출금";
            transactions = transactionRepository.findByAccountIdAndInoutTypeAndTranDateBetweenOrderByTranDateDescTranTimeDesc(account.getId(), inoutType, fromDate, toDate);
        }

        List<MockTransactionDetailDto> resList = transactions.stream()
                .map(t -> MockTransactionDetailDto.builder()
                        .tran_date(t.getTranDate())
                        .tran_time(t.getTranTime())
                        .inout_type(t.getInoutType())
                        .tran_type(t.getTranType())
                        .print_content(t.getPrintContent())
                        .tran_amt(String.valueOf(t.getTranAmt()))
                        .after_balance_amt(String.valueOf(t.getAfterBalanceAmt()))
                        .branch_name(t.getBranchName())
                        .build())
                .collect(Collectors.toList());

        return MockTransactionResponseDto.builder()
                .api_tran_id("MOCK_API_TRAN_ID_" + System.currentTimeMillis())
                .api_tran_dtm(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")))
                .rsp_code("A0000")
                .rsp_message("정상")
                .bank_tran_id("F123456789U4BC34239Z") // Mock 거래고유번호
                .bank_tran_date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")))
                .bank_name(account.getBank().getBankName())
                .fintech_use_num(account.getFintechUseNum())
                .balance_amt(String.valueOf(account.getBalance()))
                .page_record_cnt(String.valueOf(resList.size()))
                .next_page_yn("N") // Mock이므로 단순하게 'N'으로 처리
                .befor_inquiry_trace_info("")
                .res_list(resList)
                .build();
    }
}
