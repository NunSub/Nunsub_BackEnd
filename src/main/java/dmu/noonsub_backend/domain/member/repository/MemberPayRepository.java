package dmu.noonsub_backend.domain.member.repository;

import dmu.noonsub_backend.domain.member.entity.Member;
import dmu.noonsub_backend.domain.member.entity.MemberPay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberPayRepository extends JpaRepository<MemberPay, Long> {
    void deleteAllByMember(Member member);
}
