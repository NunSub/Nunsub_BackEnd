package dmu.noonsub_backend.domain.member.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class MemberCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String bankCodeStd;

    @Column(nullable = false)
    private String memberBankCode;

    @Column(nullable = false)
    private LocalDateTime inquiryAgreeDtime;

    @Builder
    public MemberCard(Member member, String bankCodeStd, String memberBankCode, LocalDateTime inquiryAgreeDtime) {
        this.member = member;
        this.bankCodeStd = bankCodeStd;
        this.memberBankCode = memberBankCode;
        this.inquiryAgreeDtime = inquiryAgreeDtime;
    }
}
