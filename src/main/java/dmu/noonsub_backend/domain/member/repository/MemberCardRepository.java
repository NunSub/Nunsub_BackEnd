package dmu.noonsub_backend.domain.member.repository;

import dmu.noonsub_backend.domain.member.entity.Member;
import dmu.noonsub_backend.domain.member.entity.MemberCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCardRepository extends JpaRepository<MemberCard, Long> {
    void deleteAllByMember(Member member);
}
