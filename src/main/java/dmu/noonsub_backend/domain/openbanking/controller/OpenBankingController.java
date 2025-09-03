package dmu.noonsub_backend.domain.openbanking.controller;

import dmu.noonsub_backend.domain.member.entity.Member;
import dmu.noonsub_backend.domain.openbanking.dto.*;
import dmu.noonsub_backend.domain.openbanking.entity.OpenBankingToken;
import dmu.noonsub_backend.domain.openbanking.service.OpenBankingService;
import dmu.noonsub_backend.global.userdetails.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bank")
public class OpenBankingController {

    private final OpenBankingService openBankingService;

    @GetMapping("/authorize")
    public ResponseEntity<AuthorizeResponseDto> authorize(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody AuthorizeRequestDto requestDto) {

        String phoneNumber = userDetails.getUsername();
        String authorizeUrl = openBankingService.getAuthorizationUrl(phoneNumber, requestDto);
        return ResponseEntity.ok(new AuthorizeResponseDto(authorizeUrl));
    }

    @PostMapping("/exchange")
    public ResponseEntity<Void> exchangeToken(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody TokenExchangeRequestDto requestDto) {

        String phoneNumber = userDetails.getUsername();
        openBankingService.exchangeTokenAndSave(phoneNumber, requestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/transactions")
    public ResponseEntity<TransactionHistoryDto.Response> getTransactions(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam("fintech_use_num") String fintechUseNum,
            @RequestParam("from_date") String fromDate,
            @RequestParam("to_date") String toDate,
            @RequestParam(value = "sort_order", defaultValue = "D") String sortOrder) {
        String phoneNumber = userDetails.getUsername();
        TransactionHistoryDto.Response response = openBankingService.getTransactionHistory(
                phoneNumber, fintechUseNum, fromDate, toDate, sortOrder
        );
        return ResponseEntity.ok(response);
    }

}
