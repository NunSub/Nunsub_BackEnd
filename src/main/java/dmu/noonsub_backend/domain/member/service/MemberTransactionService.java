package dmu.noonsub_backend.domain.member.service;

import dmu.noonsub_backend.domain.common.exception.CustomException;
import dmu.noonsub_backend.domain.common.exception.ErrorCode;
import dmu.noonsub_backend.domain.member.entity.Member;
import dmu.noonsub_backend.domain.member.entity.MemberAccounts;
import dmu.noonsub_backend.domain.member.entity.MemberTransaction;
import dmu.noonsub_backend.domain.member.enums.Category;
import dmu.noonsub_backend.domain.member.repository.TransactionRepository;
import dmu.noonsub_backend.domain.member.spec.TransactionSpecification;
import dmu.noonsub_backend.domain.openbanking.dto.TransactionDto;
import dmu.noonsub_backend.domain.openbanking.dto.TransactionPageResponseDto;
import dmu.noonsub_backend.domain.openbanking.repository.MemberBankRepository;
import dmu.noonsub_backend.domain.openbanking.service.OpenBankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberTransactionService {

    private final MemberService memberService;
    private final MemberBankRepository memberBankRepository;
    private final TransactionRepository transactionRepository;
    private final OpenBankingService openBankingService;

    @Transactional
    public TransactionPageResponseDto getAllTransactions(
            String phoneNumber, boolean refresh, Pageable pageable) {
        if (refresh) {
            // OpenBankingService의 동기화 로직 호출
            openBankingService.syncOpenBankingInfo(phoneNumber);
        }
        Specification<MemberTransaction> baseSpec = createSpecForUser(phoneNumber);
        return getTransactionsWithTotals(baseSpec, pageable);
    }

    @Transactional
    public TransactionPageResponseDto getAllTransactionsByMonth(
            String phoneNumber, String yearMonth, Pageable pageable) {
        Specification<MemberTransaction> spec = createSpecForUser(phoneNumber)
                .and(TransactionSpecification.byYearMonth(yearMonth));
        return getTransactionsWithTotals(spec, pageable);
    }

    public TransactionPageResponseDto getAllTransactionsByDate(
            String phoneNumber, String date, Pageable pageable) {
        Specification<MemberTransaction> spec = createSpecForUser(phoneNumber)
                .and(TransactionSpecification.byDate(date));
        return getTransactionsWithTotals(spec, pageable);
    }

    public TransactionPageResponseDto getAllTransactionsByInoutType(
            String phoneNumber, String inoutType, Pageable pageable) {
        Specification<MemberTransaction> spec = createSpecForUser(phoneNumber)
                .and(TransactionSpecification.byInoutType(inoutType));
        return getTransactionsWithTotals(spec, pageable);
    }

    public TransactionPageResponseDto getAllTransactionsByInoutTypeAndMonth(
            String phoneNumber, String inoutType, String yearMonth, Pageable pageable) {
        Specification<MemberTransaction> spec = createSpecForUser(phoneNumber)
                .and(TransactionSpecification.byInoutType(inoutType))
                .and(TransactionSpecification.byYearMonth(yearMonth));
        return getTransactionsWithTotals(spec, pageable);
    }

    public TransactionPageResponseDto getAllTransactionsByInoutTypeAndDate(
            String phoneNumber, String inoutType, String date, Pageable pageable) {
        Specification<MemberTransaction> spec = createSpecForUser(phoneNumber)
                .and(TransactionSpecification.byInoutType(inoutType))
                .and(TransactionSpecification.byDate(date));
        return getTransactionsWithTotals(spec, pageable);
    }

    // --- 특정 계좌 거래내역 조회 ---

    public TransactionDto.Response getAccountTransactions(
            String phoneNumber, boolean refresh, String fintechUseNum, Pageable pageable)
    {
        if (refresh) {
            // OpenBankingService의 동기화 로직 호출
            openBankingService.syncOpenBankingInfo(phoneNumber);
        }

        MemberAccounts memberAccount = findAndVerifyAccount(phoneNumber, fintechUseNum);
        Specification<MemberTransaction> spec = createSpecForAccount(phoneNumber, fintechUseNum);
        Page<MemberTransaction> transactionPage = transactionRepository.findAll(spec, pageable);
        return buildSingleAccountResponse(memberAccount, transactionPage);
    }

    public TransactionDto.Response getAccountTransactionsByMonth(
            String phoneNumber, String fintechUseNum, String yearMonth, Pageable pageable)
    {
        MemberAccounts memberAccount = findAndVerifyAccount(phoneNumber, fintechUseNum);
        Specification<MemberTransaction> spec = createSpecForAccount(phoneNumber, fintechUseNum)
                .and(TransactionSpecification.byYearMonth(yearMonth));
        Page<MemberTransaction> transactionPage = transactionRepository.findAll(spec, pageable);
        return buildSingleAccountResponse(memberAccount, transactionPage);
    }

    public TransactionDto.Response getAccountTransactionsByDate(
            String phoneNumber, String fintechUseNum, String date, Pageable pageable)
    {
        MemberAccounts memberAccount = findAndVerifyAccount(phoneNumber, fintechUseNum);
        Specification<MemberTransaction> spec = createSpecForAccount(phoneNumber, fintechUseNum)
                .and(TransactionSpecification.byDate(date));
        Page<MemberTransaction> transactionPage = transactionRepository.findAll(spec, pageable);
        return buildSingleAccountResponse(memberAccount, transactionPage);
    }


    // --- Private Helper Methods ---

    /**
     * Specification을 받아 페이징 조회 후 DTO로 변환하여 반환하는 공통 메소드
     */
    private TransactionPageResponseDto getTransactionsWithTotals(
            Specification<MemberTransaction> spec, Pageable pageable) {
        Page<MemberTransaction> transactionPage = transactionRepository.findAll(spec, pageable);
        Page<TransactionDto.Transaction> transactionDtoPage = transactionPage.map(this::convertToTransactionDto);

        Specification<MemberTransaction> depositSpec = Specification.where(spec)
                .and(TransactionSpecification.byInoutType("입금"));
        long totalDeposit = transactionRepository.calculateTotalAmount(depositSpec);

        Specification<MemberTransaction> withdrawalSpec = Specification.where(spec)
                .and(TransactionSpecification.byInoutType("출금"));
        long totalWithdrawal = transactionRepository.calculateTotalAmount(withdrawalSpec);

        return new TransactionPageResponseDto(transactionDtoPage, totalDeposit, totalWithdrawal);
    }

    /**
     * 특정 사용자의 모든 계좌에 대한 Specification을 생성
     */
    private Specification<MemberTransaction> createSpecForUser(String phoneNumber) {
        Member member = memberService.getMemberByPhoneNumber(phoneNumber);
        List<MemberAccounts> allMemberAccounts = memberBankRepository.findAllByMember(member);
        return Specification.where(TransactionSpecification.forAccounts(allMemberAccounts));
    }

    /**
     * 특정 사용자의 특정 계좌에 대한 Specification을 생성
     */
    private Specification<MemberTransaction> createSpecForAccount(String phoneNumber, String fintechUseNum) {
        Member member = memberService.getMemberByPhoneNumber(phoneNumber);
        MemberAccounts memberAccount = memberBankRepository.findByMemberAndFintechUseNum(member,
                        fintechUseNum)
                .orElseThrow(() -> new CustomException(ErrorCode.BANK_ACCOUNT_NOT_FOUND));
        return Specification.where(TransactionSpecification.forAccounts(List.of(memberAccount)));
    }

    /**
     * [신규] 계좌 조회 및 소유권 검증을 위한 헬퍼 메소드
     */
    private MemberAccounts findAndVerifyAccount(String phoneNumber, String fintechUseNum) {
        Member member = memberService.getMemberByPhoneNumber(phoneNumber);
        return memberBankRepository.findByMemberAndFintechUseNum(member, fintechUseNum)
                .orElseThrow(() -> new CustomException(ErrorCode.BANK_ACCOUNT_NOT_FOUND));
    }

    /**
     * 특정 계좌의 페이징된 거래내역을 최종 응답 DTO로 구성
     */
    private TransactionDto.Response buildSingleAccountResponse(
            MemberAccounts account, Page<MemberTransaction> transactionPage) {
        List<TransactionDto.Transaction> transactionDtos = transactionPage.getContent().stream()
                .map(this::convertToTransactionDto)
                .collect(Collectors.toList());

        return TransactionDto.Response.builder()
                .bankName(account.getBankName())
                .accountAlias(account.getAccountAlias())
                .accountNumMasked(account.getAccountNumMasked())
                .resList(transactionDtos)
                .pageNumber(transactionPage.getNumber())
                .pageSize(transactionPage.getSize())
                .totalPages(transactionPage.getTotalPages())
                .totalElements(transactionPage.getTotalElements())
                .last(transactionPage.isLast())
                .build();
    }

    /**
     * MemberTransaction Entity를 Transaction DTO로 변환
     */
    private TransactionDto.Transaction convertToTransactionDto(MemberTransaction tx) {
        return TransactionDto.Transaction.builder()
                .tranDate(tx.getTranDate())
                .tranTime(tx.getTranTime())
                .inoutType(tx.getInoutType())
                .tranType(tx.getTranType())
                .printContent(tx.getPrintContent())
                .tranAmt(String.valueOf(tx.getTranAmt()))
                .afterBalanceAmt(String.valueOf(tx.getAfterBalanceAmt()))
                .branchName(tx.getBranchName())
                .category(tx.getCategory().getDescription())
                .build();
    }

    @Transactional
    public void updateTransactionCategory(Long transactionId, String category, String phoneNumber) {
        Member member = memberService.getMemberByPhoneNumber(phoneNumber);
        MemberTransaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new CustomException(ErrorCode.TRANSACTION_NOT_FOUND));

        boolean isOwner = transaction.getMemberAccount().getMember().equals(member);
        if(!isOwner) {
            throw new CustomException(ErrorCode.FORBIDDEN);
        }

        transaction.updateCategory(Category.fromString(category));
    }
}

