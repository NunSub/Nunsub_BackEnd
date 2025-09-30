package dmu.noonsub_backend.mockapi.service;

import dmu.noonsub_backend.mockapi.dto.*;
import dmu.noonsub_backend.mockapi.model.MockAccount;
import dmu.noonsub_backend.mockapi.model.MockTransaction;
import dmu.noonsub_backend.mockapi.repository.MockAccountRepository;
import dmu.noonsub_backend.mockapi.repository.MockTransactionRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MockTransferService {

    private final MockAccountRepository accountRepository; // 수정
    private final MockTransactionRepository transactionRepository;
    private final EntityManager entityManager;
    private static final int PAGE_SIZE = 25; // 한 페이지에 표시할 거래 내역 수

    @Transactional // DB 데이터 변경이 있으므로 트랜잭션 처리
    public WithdrawResponseDto processWithdrawal(WithdrawRequestDto dto) {
        Optional<MockAccount> accountOpt = accountRepository.findByFintechUseNum(dto.getFintechUseNum());

        if (accountOpt.isEmpty()) {
            return WithdrawResponseDto.builder().rspCode("A0304").rspMessage("핀테크이용번호 불일치").build();
        }

        MockAccount account = accountOpt.get();
        long requestAmount = Long.parseLong(dto.getTranAmt());

        if (account.getBalance() < requestAmount) {
            return WithdrawResponseDto.builder().rspCode("A0008").rspMessage("잔액 부족")
                    .wdLimitRemainAmt(String.valueOf(account.getBalance())).build();
        }

        // 잔액 차감
        account.setBalance(account.getBalance() - requestAmount);
        accountRepository.save(account); // 변경된 내용을 DB에 저장

        return WithdrawResponseDto.builder()
                .apiTranId("MOCK_API_TRAN_ID_" + System.currentTimeMillis())
                .rspCode("A0000").rspMessage("처리 성공")
                .fintechUseNum(dto.getFintechUseNum())
                .tranAmt(dto.getTranAmt())
                .wdLimitRemainAmt(String.valueOf(account.getBalance()))
                .build();
    }

    @Transactional(readOnly = true) // 읽기 전용 트랜잭션
    public MockBalanceResponseDto getBalance(String fintechUseNum) {
        return accountRepository.findByFintechUseNum(fintechUseNum)
                .map(account -> MockBalanceResponseDto.builder()
                        .apiTranId("MOCK_API_TRAN_ID_" + System.currentTimeMillis())
                        .apiTranDtm(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")))
                        .rspCode("A0000").rspMessage("정상")
                        .balanceAmt(String.valueOf(account.getBalance()))
                        .build())
                .orElse(MockBalanceResponseDto.builder()
                        .apiTranId("MOCK_API_TRAN_ID_" + System.currentTimeMillis())
                        .apiTranDtm(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")))
                        .rspCode("A0304").rspMessage("핀테크이용번호 불일치")
                        .balanceAmt("0")
                        .build());
    }

    @Transactional(readOnly = true)
    public MockTransactionResponseDto getTransactionHistory(
            String fintechUseNum, String fromDate, String toDate,
            String inquiryType, String beforeInquiryTraceInfo) {
        Optional<MockAccount> accountOpt = accountRepository.findByFintechUseNum(fintechUseNum);

        if (accountOpt.isEmpty()) {
            return MockTransactionResponseDto.builder().rspCode("A0304").rspMessage("핀테크이용번호 불일치").build();
        }

        MockAccount account = accountOpt.get();

        // 페이징 처리 로직 추가
        int page = 0; // 기본 페이지 0
        if (StringUtils.hasText(beforeInquiryTraceInfo)) {
            try {
                page = Integer.parseInt(beforeInquiryTraceInfo);
            } catch (NumberFormatException e) {
                // 파라미터가 숫자가 아니면 그냥 0페이지로 처리
                page = 0;
            }
        }

        int pageSize = 25; // API 명세에 따라 한 페이지 최대 25건
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<MockTransaction> transactionsPage;

        // 조회구분코드(inquiryType)에 따른 분기 처리
        if ("A".equals(inquiryType)) {
            transactionsPage = transactionRepository.findByAccountIdAndTranDateBetweenOrderByTranDateDescTranTimeDesc(
                    account.getId(), fromDate, toDate, pageable);
        } else {
            String inoutType = "I".equals(inquiryType) ? "입금" : "출금";
            transactionsPage = transactionRepository.findByAccountIdAndInoutTypeAndTranDateBetweenOrderByTranDateDescTranTimeDesc(
                    account.getId(), inoutType, fromDate, toDate, pageable);
        }

        List<MockTransactionDetailDto> resList = transactionsPage.getContent().stream()
                .map(t -> MockTransactionDetailDto.builder()
                        .tranDate(t.getTranDate())
                        .tranTime(t.getTranTime())
                        .inoutType(t.getInoutType())
                        .tranType(t.getTranType())
                        .printContent(t.getPrintContent())
                        .tranAmt(String.valueOf(t.getTranAmt()))
                        .afterBalanceAmt(String.valueOf(t.getAfterBalanceAmt()))
                        .branchName(t.getBranchName())
                        .build())
                .collect(Collectors.toList());

        boolean hasNextPage = transactionsPage.hasNext();

        String nextPageTraceInfo = null;
        if (hasNextPage) {
            nextPageTraceInfo = String.valueOf(page + 1);
        }

        return MockTransactionResponseDto.builder()
                .apiTranId("MOCK_API_TRAN_ID_" + System.currentTimeMillis())
                .apiTranDtm(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")))
                .rspCode("A0000")
                .rspMessage("정상")
                .bankTranId("F123456789U4BC34239Z")
                .bankTranDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")))
                .bankName(account.getBank().getBankName())
                .fintechUseNum(account.getFintechUseNum())
                .balanceAmt(String.valueOf(account.getBalance()))
                .pageRecordCnt(String.valueOf(transactionsPage.getNumberOfElements()))
                .nextPageYn(hasNextPage ? "Y" : "N")
                .beforeInquiryTraceInfo(nextPageTraceInfo)
                .resList(resList)
                .build();
    }
}
