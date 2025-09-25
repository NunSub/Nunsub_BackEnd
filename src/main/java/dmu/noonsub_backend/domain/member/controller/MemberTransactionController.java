package dmu.noonsub_backend.domain.member.controller;

import dmu.noonsub_backend.domain.common.dto.PageResponseDto;
import dmu.noonsub_backend.domain.member.service.MemberTransactionService;
import dmu.noonsub_backend.domain.openbanking.dto.TransactionDto;
import dmu.noonsub_backend.global.userdetails.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Transaction (거래내역)", description = "DB에 저장된 거래내역 조회 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class MemberTransactionController {

    private final MemberTransactionService transactionService;
    private final Sort LATEST_SORT = Sort.by("tranDate").descending().and(Sort.by("tranTime").descending());

    // --- 전체 계좌 거래내역 ---
    @Operation(summary = "모든 계좌 거래내역 페이징 조회", description = "인증된 사용자의 모든 계좌에 대한 전체 거래내역을 최신순으로 페이징하여 조회합니다.")
    @GetMapping
    public ResponseEntity<PageResponseDto<TransactionDto.Transaction>> getAllTransactions(
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam(defaultValue = "false") boolean refresh,
            @Parameter(description = "페이지 번호 (0부터 시작)", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 당 항목 수", example = "20") @RequestParam(defaultValue = "20") int size) {
        String phoneNumber = userDetails.getUsername();
        Pageable pageable = PageRequest.of(page, size, LATEST_SORT);
        PageResponseDto<TransactionDto.Transaction> response =
                transactionService.getAllTransactions(phoneNumber, refresh,  pageable);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "모든 계좌 월별 거래내역 페이징 조회", description = "인증된 사용자의 모든 계좌에 대해 특정 '연월'의 거래내역을 최신순으로 페이징하여 조회합니다.")
    @GetMapping("/by-month")
    public ResponseEntity<PageResponseDto<TransactionDto.Transaction>> getAllTransactionsByMonth(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Parameter(description = "조회할 연월 (형식: yyyy-MM)", required = true, example = "2023-09")
            @RequestParam String yearMonth,
            @Parameter(description = "페이지 번호 (0부터 시작)", example = "0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 당 항목 수", example = "20")
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size, LATEST_SORT);
        PageResponseDto<TransactionDto.Transaction> response =
                transactionService.getAllTransactionsByMonth(userDetails.getUsername(), yearMonth, pageable);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "모든 계좌 일별 거래내역 페이징 조회", description = "인증된 사용자의 모든 계좌에 대해 특정 '일자'의 거래내역을 최신순으로 페이징하여 조회합니다.")
    @GetMapping("/by-date")
    public ResponseEntity<PageResponseDto<TransactionDto.Transaction>> getAllTransactionsByDate(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Parameter(description = "조회할 일자 (형식: yyyy-MM-dd)", required = true, example = "2023-09-25")
            @RequestParam String date,
            @Parameter(description = "페이지 번호 (0부터 시작)", example = "0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 당 항목 수", example = "20")
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size, LATEST_SORT);
        PageResponseDto<TransactionDto.Transaction> response =
                transactionService.getAllTransactionsByDate(userDetails.getUsername(), date, pageable);
        return ResponseEntity.ok(response);
    }

    // --- 특정 계좌 거래내역 ---
    @Operation(summary = "특정 계좌 거래내역 페이징 조회", description = "특정 계좌(fintechUseNum)의 전체 거래내역을 최신순으로 페이징하여 조회합니다.")
    @GetMapping("/{fintechUseNum}")
    public ResponseEntity<TransactionDto.Response> getAccountTransactions(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Parameter(description = "조회할 계좌의 핀테크 이용번호", required = true)
            @PathVariable String fintechUseNum,
            @RequestParam(defaultValue = "false") boolean refresh,
            @Parameter(description = "페이지 번호 (0부터 시작)", example = "0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 당 항목 수", example = "20")
            @RequestParam(defaultValue = "20") int size) {
        String phoneNumber = userDetails.getUsername();
        Pageable pageable = PageRequest.of(page, size, LATEST_SORT);

        // 서비스 호출 시 refresh 플래그 전달
        TransactionDto.Response response =
                transactionService.getAccountTransactions(phoneNumber, refresh, fintechUseNum, pageable);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "특정 계좌 월별 거래내역 페이징 조회", description = "특정 계좌(fintechUseNum)에 대해 특정 '연월'의 거래내역을 최신순으로 페이징하여 조회합니다.")
    @GetMapping("/{fintechUseNum}/by-month")
    public ResponseEntity<TransactionDto.Response> getAccountTransactionsByMonth(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Parameter(description = "조회할 계좌의 핀테크 이용번호", required = true)
            @PathVariable String fintechUseNum,
            @Parameter(description = "조회할 연월 (형식: yyyy-MM)", required = true, example = "2023-09")
            @RequestParam String yearMonth,
            @Parameter(description = "페이지 번호 (0부터 시작)", example = "0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 당 항목 수", example = "20")
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size, LATEST_SORT);
        TransactionDto.Response response =
                transactionService.getAccountTransactionsByMonth(userDetails.getUsername(), fintechUseNum, yearMonth,
                        pageable);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "특정 계좌 일별 거래내역 페이징 조회", description = "특정 계좌(fintechUseNum)에 대해 특정 '일자'의 거래내역을 최신순으로 페이징하여 조회합니다.")
    @GetMapping("/{fintechUseNum}/by-date")
    public ResponseEntity<TransactionDto.Response> getAccountTransactionsByDate(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Parameter(description = "조회할 계좌의 핀테크 이용번호", required = true)
            @PathVariable String fintechUseNum,
            @Parameter(description = "조회할 일자 (형식: yyyy-MM-dd)", required = true, example = "2023-09-25")
            @RequestParam String date,
            @Parameter(description = "페이지 번호 (0부터 시작)", example = "0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 당 항목 수", example = "20")
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size, LATEST_SORT);
        TransactionDto.Response response =
                transactionService.getAccountTransactionsByDate(userDetails.getUsername(), fintechUseNum, date, pageable);
        return ResponseEntity.ok(response);
    }
}
