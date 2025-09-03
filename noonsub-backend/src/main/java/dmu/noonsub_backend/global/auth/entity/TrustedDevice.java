package dmu.noonsub_backend.global.auth.entity;

import dmu.noonsub_backend.domain.common.BaseEntity;
import dmu.noonsub_backend.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrustedDevice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String deviceId;

    @Column(nullable = false)
    private String deviceName;

    @Column(length = 512, nullable = false)
    private String refreshToken;

    private LocalDateTime lastUsedAt;

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        this.lastUsedAt = LocalDateTime.now();
    }

}
