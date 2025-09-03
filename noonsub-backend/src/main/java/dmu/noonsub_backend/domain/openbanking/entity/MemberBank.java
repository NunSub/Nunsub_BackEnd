package dmu.noonsub_backend.domain.openbanking.entity;

import dmu.noonsub_backend.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor
public class MemberBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String bankCode; // 은행 코드

    @Column(nullable = false, unique = true)
    private String fintechUseNum; // 핀테크 이용 번호

    @Column(nullable = false)
    private String userSeqNo; // 사용자 일련번호

    @Lob
    @Column(nullable = false)
    private String accessTokenEnc; // 암호화된 Access Token

    @Lob
    @Column(nullable = false)
    private String refreshTokenEnc; // 암호화된 Refresh Token

    @Column(nullable = false)
    private Instant accessTokenExpiresAt; // Access Token 만료 시간

    @Column(nullable = false)
    private Instant linkedAt; // 연결된 시각

    private boolean active = true; // 활성 상태

    @Builder
    public MemberBank(Member member, String bankCode, String fintechUseNum, String userSeqNo,
                      String accessTokenEnc, String refreshTokenEnc, Instant accessTokenExpiresAt) {
        this.member = member;
        this.bankCode = bankCode;
        this.fintechUseNum = fintechUseNum;
        this.userSeqNo = userSeqNo;
        this.accessTokenEnc = accessTokenEnc;
        this.refreshTokenEnc = refreshTokenEnc;
        this.accessTokenExpiresAt = this.accessTokenExpiresAt;
    }

    public void deactivate() {
        this.active = false;
    }

    public void updateTokens(String accessTokenEnc, String refreshTokenEnc, Instant accessTokenExpiresAt) {
        this.accessTokenEnc = accessTokenEnc;
        this.refreshTokenEnc = refreshTokenEnc;
        this.accessTokenExpiresAt = accessTokenExpiresAt;
    }

}
