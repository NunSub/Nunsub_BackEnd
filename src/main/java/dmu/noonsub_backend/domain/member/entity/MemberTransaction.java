package dmu.noonsub_backend.domain.member.entity;

import dmu.noonsub_backend.domain.member.enums.Category;
import dmu.noonsub_backend.domain.subscription.entity.Subscription;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
public class MemberTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_transaction_seq")
    @SequenceGenerator(
            name = "member_transaction_seq", // 매핑할 시퀀스 생성기 이름
            sequenceName = "MEMBER_TRANSACTION_SEQ", // 데이터베이스에 생성될 시퀀스 이름
            initialValue = 1, // 시작 값
            allocationSize = 50 // 시퀀스 한 번 호출 시 미리 할당할 ID 개수 (batch_size와 맞추는 것을 권장)
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_account_id", nullable = false)
    private MemberAccounts memberAccount; // 어떤 계좌의 거래내역인지 연결

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    private String tranDate;
    private String tranTime;
    private String inoutType;
    private String tranType;
    private String printContent;
    private Long tranAmt;
    private Long afterBalanceAmt;
    private String branchName;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Builder
    public MemberTransaction(MemberAccounts memberAccount, String tranDate, String tranTime, String inoutType, String tranType, String printContent, Long tranAmt, Long afterBalanceAmt, String branchName, Category category) {
        this.memberAccount = memberAccount;
        this.tranDate = tranDate;
        this.tranTime = tranTime;
        this.inoutType = inoutType;
        this.tranType = tranType;
        this.printContent = printContent;
        this.tranAmt = tranAmt;
        this.afterBalanceAmt = afterBalanceAmt;
        this.branchName = branchName;
        this.category = category;
    }

    public void updateCategory(Category category) {
        this.category = category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
}
