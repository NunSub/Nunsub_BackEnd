package dmu.noonsub_backend.domain.openbanking.repository;

import dmu.noonsub_backend.domain.member.entity.Member;
import dmu.noonsub_backend.domain.member.entity.MemberAccounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberBankRepository extends JpaRepository<MemberAccounts,String> {
    Optional<MemberAccounts> findByMemberAndFintechUseNum(Member member, String fintechUseNum);
    List<MemberAccounts> findAllByMember(Member member);

    void deleteAllByMember(Member member);
}
