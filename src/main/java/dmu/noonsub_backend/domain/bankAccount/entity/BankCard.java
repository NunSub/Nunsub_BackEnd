package dmu.noonsub_backend.domain.bankAccount.entity;

import dmu.noonsub_backend.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private String bankCodeStd;        // 카드사 대표코드
    private String memberBankCode;     // 회원 금융회사 코드
    private String inquiryAgreeDtime;  // 조회 동의일시
}
