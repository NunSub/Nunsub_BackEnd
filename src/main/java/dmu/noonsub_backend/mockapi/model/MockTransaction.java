package dmu.noonsub_backend.mockapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "mock_transactions")
public class MockTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tranDate; // 거래일자
    private String tranTime; // 거래시간
    private String inoutType; // 입출금구분 ("입금", "출금")
    private String tranType; // 거래구분
    private String printContent; // 통장인자내용
    private long tranAmt; // 거래금액
    private long afterBalanceAmt; // 거래후잔액
    private String branchName; // 거래점명

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private MockAccount account;

    public MockTransaction(MockAccount account, String tranDate, String tranTime, String inoutType, String printContent, long tranAmt) {
        this.account = account;
        this.tranDate = tranDate;
        this.tranTime = tranTime;
        this.inoutType = inoutType;
        this.printContent = printContent;
        this.tranAmt = tranAmt;

        // 거래 후 잔액 계산
        long currentBalance = account.getBalance();
        if ("출금".equals(inoutType)) {
            this.afterBalanceAmt = currentBalance - tranAmt;
        } else {
            this.afterBalanceAmt = currentBalance + tranAmt;
        }

        // 나머지 필드 Mock 데이터
        this.tranType = "현금";
        this.branchName = "오픈은행 본점";
    }
}
