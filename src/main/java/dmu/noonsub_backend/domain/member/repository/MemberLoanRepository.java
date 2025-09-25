package dmu.noonsub_backend.domain.member.repository;

import dmu.noonsub_backend.domain.member.entity.Member;
import dmu.noonsub_backend.domain.member.entity.MemberLoan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberLoanRepository extends JpaRepository<MemberLoan, Long> {
    void deleteAllByMember(Member member);
}
