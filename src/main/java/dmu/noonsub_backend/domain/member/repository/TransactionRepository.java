package dmu.noonsub_backend.domain.member.repository;

import dmu.noonsub_backend.domain.member.entity.Member;
import dmu.noonsub_backend.domain.member.entity.MemberAccounts;
import dmu.noonsub_backend.domain.member.entity.MemberTransaction;
import dmu.noonsub_backend.domain.member.enums.Category;
import dmu.noonsub_backend.domain.subscription.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends
        JpaRepository<MemberTransaction, Long>,
        JpaSpecificationExecutor<MemberTransaction>,
        TransactionRepositoryCustom {
    // 특정 계좌에 속한 모든 거래내역을 삭제하기 위한 메서드
    void deleteAllByMemberAccount(MemberAccounts memberAccount);

    List<MemberTransaction> findByMemberAccount_MemberAndTranDateBetween(Member memberAccountMember, String tranDateAfter, String tranDateBefore);

    Optional<MemberTransaction> findTopBySubscriptionOrderByTranDateDescTranTimeDesc(Subscription subscription);
}
