package dmu.noonsub_backend.domain.member.repository;

import dmu.noonsub_backend.domain.member.entity.Member;
import dmu.noonsub_backend.domain.member.entity.MemberInsurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberInsuranceRepository extends JpaRepository<MemberInsurance, Long> {
    void deleteAllByMember(Member member);

}
