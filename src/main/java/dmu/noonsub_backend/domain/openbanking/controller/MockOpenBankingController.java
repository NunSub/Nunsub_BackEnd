package dmu.noonsub_backend.domain.openbanking.controller;

import dmu.noonsub_backend.domain.openbanking.dto.mock.TokenResponse;
import dmu.noonsub_backend.domain.openbanking.dto.mock.TransactionHistoryResponse;
import dmu.noonsub_backend.domain.openbanking.dto.mock.UserInfoResponse;
import dmu.noonsub_backend.domain.openbanking.entity.mock.MockAccount;
import dmu.noonsub_backend.domain.openbanking.entity.mock.MockTransaction;
import dmu.noonsub_backend.domain.openbanking.repository.mock.MockAccountRepository;
import dmu.noonsub_backend.domain.openbanking.repository.mock.MockTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mock/openbanking")
@RequiredArgsConstructor
public class MockOpenBankingController {

    // 리포지토리 의존성 주입
    private final MockAccountRepository mockAccountRepository;
    private final MockTransactionRepository mockTransactionRepository;

    @PostMapping("/oauth/2.0/token")
    public ResponseEntity<TokenResponse> exchangeToken(@RequestParam("grant_type") String grantType) {
        TokenResponse response = new TokenResponse();
        response.setAccessToken("mock_access_token_" + UUID.randomUUID());
        response.setTokenType("Bearer");
        response.setExpiresIn(86400);
        response.setRefreshToken("mock_refresh_token_" + UUID.randomUUID());
        response.setScope("inquiry login transfer");
        response.setUserSeqNo("mock_user_seq_no_" + UUID.randomUUID());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/v2.0/user/me")
    public ResponseEntity<UserInfoResponse> getUserInfo(@RequestParam("user_seq_no") String userSeqNo) {
        // DB에서 모든 Mock 계좌 정보를 조회
        List<MockAccount> accounts = mockAccountRepository.findAll();

        // 조회된 엔티티를 응답 DTO로 변환
        List<UserInfoResponse.AccountInfo> accountInfos = accounts.stream()
                .map(account -> new UserInfoResponse.AccountInfo(
                        account.getFintechUseNum(),
                        account.getAccountAlias(),
                        account.getBankCodeStd(),
                        account.getAccountNumMasked(),
                        "입출금", // 예시 값
                        "KRW",   // 예시 값
                        null     // 등록일은 현재 명세에 없으므로 null
                ))
                .collect(Collectors.toList());

        UserInfoResponse response = new UserInfoResponse();
        response.setApiTranId(UUID.randomUUID().toString());
        response.setRspCode("A0000");
        response.setRspMessage("Success");
        response.setUserSeqNo(userSeqNo);
        response.setUserInfo(new UserInfoResponse.UserInfo("김눈섭", "19950115", "M", "Y", "01012345678"));
        response.setResList(accountInfos); // DTO로 변환된 계좌 목록을 설정

        return ResponseEntity.ok(response);
    }

    @GetMapping("/v2.0/account/transaction_list/fin_num")
    public ResponseEntity<TransactionHistoryResponse> getTransactionHistory(
            @RequestParam("fintech_use_num") String fintechUseNum) {

        // 요청된 핀테크이용번호로 계좌와 거래내역을 DB에서 조회
        MockAccount account = mockAccountRepository.findByFintechUseNum(fintechUseNum)
                .orElseThrow(() -> new IllegalArgumentException("계좌를 찾을 수 없습니다."));
        List<MockTransaction> transactions = mockTransactionRepository.findByFintechUseNumOrderByTranDateDescTranTimeDesc(fintechUseNum);

        // 조회된 거래내역 엔티티를 응답 DTO로 변환
        List<TransactionHistoryResponse.TransactionItem> transactionItems = transactions.stream()
                .map(tx -> new TransactionHistoryResponse.TransactionItem(
                        tx.getTranDate().format(DateTimeFormatter.ofPattern("yyyyMMdd")),
                        tx.getTranTime().format(DateTimeFormatter.ofPattern("HHmmss")),
                        tx.getTranType(),
                        String.valueOf(tx.getTranAmt()),
                        tx.getPrintContent(),
                        String.valueOf(tx.getAfterBalanceAmt())
                ))
                .collect(Collectors.toList());

        TransactionHistoryResponse response = new TransactionHistoryResponse();
        response.setApiTranId(UUID.randomUUID().toString());
        response.setRspCode("A0000");
        response.setRspMessage("Success");
        response.setBankName(account.getBankName());
        response.setAccountNumMasked(account.getAccountNumMasked());
        response.setAccountAlias(account.getAccountAlias());
        response.setResList(transactionItems); // DTO로 변환된 거래내역 목록을 설정

        return ResponseEntity.ok(response);
    }
}
