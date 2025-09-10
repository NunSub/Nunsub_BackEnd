package dmu.noonsub_backend.domain.openbanking.repository.mock;

import dmu.noonsub_backend.domain.openbanking.entity.mock.MockAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MockAccountRepository extends JpaRepository<MockAccount, Long> {
    Optional<MockAccount> findByFintechUseNum(String fintechUseNum);
}
