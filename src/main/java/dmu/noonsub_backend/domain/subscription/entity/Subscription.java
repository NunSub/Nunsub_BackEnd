package dmu.noonsub_backend.domain.subscription.entity;

import dmu.noonsub_backend.domain.common.BaseEntity;
import dmu.noonsub_backend.domain.member.entity.Member;
import dmu.noonsub_backend.domain.member.entity.MemberAccounts;
import dmu.noonsub_backend.domain.subscription.enums.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "subscription", uniqueConstraints = {
        @UniqueConstraint(
                name = "uk_subscription_member_service",
                columnNames = {"member_id", "serviceName"}
        )
})
public class Subscription extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String serviceName;

    @Column(nullable = false)
    private Integer paymentAmount;

    @Column(nullable = false)
    private LocalDate nextPaymentDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubscriptionStatus subStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_account_id")
    private MemberAccounts paymentAccount;

    //== 정보 업데이트를 위한 편의 메소드 ==//

    /**
     * 다음 결제 예정일 업데이트
     */
    public void updateNextPaymentDate(LocalDate nextPaymentDate) {
        this.nextPaymentDate = nextPaymentDate;
    }

    /**
     * 결제 금액을 업데이트
     */
    public void updateAmount(Integer newAmount) {
        this.paymentAmount = newAmount;
    }

    /**
     * 구독 비활성화
     */
    public void deactivate() {
        this.subStatus = SubscriptionStatus.INACTIVE;
    }

    /**
     * 구독 활성화
     */
    public void reactivate(LocalDate nextPaymentDate, Integer paymentAmount) {
        this.subStatus = SubscriptionStatus.ACTIVE;
        this.nextPaymentDate = nextPaymentDate;
        this.paymentAmount = paymentAmount;
    }

    public void updatePaymentAccount(MemberAccounts detectedAccount) {
        this.paymentAccount = detectedAccount;
    }
}
