package dmu.noonsub_backend.domain.openbanking.repository;

import dmu.noonsub_backend.domain.member.entity.Member;
import dmu.noonsub_backend.domain.openbanking.entity.OpenBankingUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OpenBankingUserInfoRepository extends JpaRepository<OpenBankingUserInfo, Long> {

    Optional<OpenBankingUserInfo> findByMember(Member member);

    void deleteByMember(Member member);
}
