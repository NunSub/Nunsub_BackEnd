package dmu.noonsub_backend.mockapi.repository;

import dmu.noonsub_backend.mockapi.model.MockTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MockTransactionRepository extends JpaRepository<MockTransaction, Long> {
    // 계좌 ID와 기간으로 거래내역 조회 (정렬 포함)
    List<MockTransaction> findByAccountIdAndTranDateBetweenOrderByTranDateDescTranTimeDesc(
            Long accountId, String fromDate, String toDate);

    // 입출금 유형과 기간으로 거래내역 조회 (정렬 포함)
    List<MockTransaction> findByAccountIdAndInoutTypeAndTranDateBetweenOrderByTranDateDescTranTimeDesc(
            Long accountId, String inoutType, String fromDate, String toDate);
}
