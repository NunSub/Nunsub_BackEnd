package dmu.noonsub_backend.domain.openbanking.repository;

import dmu.noonsub_backend.domain.member.entity.Member;
import dmu.noonsub_backend.domain.openbanking.entity.OpenBankingToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OpenBankingTokenRepository extends JpaRepository<OpenBankingToken, Long>{
    Optional<OpenBankingToken> findByMember(Member member);
    void deleteByMember(Member member);
}
