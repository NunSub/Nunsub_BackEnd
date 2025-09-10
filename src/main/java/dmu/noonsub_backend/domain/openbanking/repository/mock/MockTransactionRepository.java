package dmu.noonsub_backend.domain.openbanking.repository.mock;

import dmu.noonsub_backend.domain.openbanking.entity.mock.MockTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MockTransactionRepository extends JpaRepository<MockTransaction, Long> {
    List<MockTransaction> findByFintechUseNumOrderByTranDateDescTranTimeDesc(String fintechUseNum);
}
