package dmu.noonsub_backend.domain.openbanking.repository;

import dmu.noonsub_backend.domain.member.entity.Member;
import dmu.noonsub_backend.domain.openbanking.entity.MemberBank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberBankRepository extends JpaRepository<MemberBank,String> {
    Optional<MemberBank> findByMemberAndFintechUseNum(Member member, String fintechUseNum);
    List<MemberBank> findAllByMemberAndActiveTrue(Member member);
}
