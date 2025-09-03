package dmu.noonsub_backend.domain.openbanking.entity;

import dmu.noonsub_backend.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OpenBankingUserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Member member;

    private String apiTranId;         // 거래고유번호
    private String apiTranDtm;        // 거래일시 (17자리)

    private String userSeqNo;         // 사용자 일련번호
    private String userCi;            // 사용자 CI
    private String userName;          // 사용자 이름

    private LocalDateTime syncedAt;   // 저장 시점
}
