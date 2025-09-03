package dmu.noonsub_backend.domain.subcribtion.entity;

import dmu.noonsub_backend.domain.member.entity.Member;
import dmu.noonsub_backend.domain.subcribtion.enums.PaymentMethod;
import dmu.noonsub_backend.domain.subcribtion.enums.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "subscription")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String serviceName;

    private String planName;

    @Column(nullable = false)
    private BigDecimal monthlyFee;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(nullable = false)
    private boolean autoRenewal = true;

    @Column(nullable = false)
    private LocalDate subscriptionStartDate;

    private LocalDate subscriptionEndDate;  // NULL이면 무기한

    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
