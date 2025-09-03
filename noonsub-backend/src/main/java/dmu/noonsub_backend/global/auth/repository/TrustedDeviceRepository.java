package dmu.noonsub_backend.global.auth.repository;

import dmu.noonsub_backend.domain.member.entity.Member;
import dmu.noonsub_backend.global.auth.entity.TrustedDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrustedDeviceRepository extends JpaRepository<TrustedDevice, Long> {
    Optional<TrustedDevice> findByMemberAndDeviceId(Member member, String deviceId);
    Optional<TrustedDevice> findByRefreshTokenAndDeviceId(String refreshToken, String deviceId);
    List<TrustedDevice> findAllByMember(Member member);
    void deleteByMemberAndDeviceId(Member member, String deviceId);
}
