package dmu.noonsub_backend.domain.member.repository;

import dmu.noonsub_backend.domain.member.entity.MemberAccounts;
import dmu.noonsub_backend.domain.member.entity.MemberTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TransactionRepository extends
        JpaRepository<MemberTransaction, Long>,
        JpaSpecificationExecutor<MemberTransaction>,
        TransactionRepositoryCustom {
    // 특정 계좌에 속한 모든 거래내역을 삭제하기 위한 메서드
    void deleteAllByMemberAccount(MemberAccounts memberAccount);
}
