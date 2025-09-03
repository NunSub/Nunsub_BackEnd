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
public class OpenBankingToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Member member;

    @Column
    private String accessToken;
    @Column
    private String refreshToken;
    @Column
    private String userSeqNo;

    @Column
    private Long expiresIn;
    @Column
    private LocalDateTime issuedAt;

}
