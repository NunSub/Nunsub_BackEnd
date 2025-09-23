package dmu.noonsub_backend.mockapi.repository;

import dmu.noonsub_backend.mockapi.model.MockAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MockAccountRepository extends JpaRepository<MockAccount, Integer> {
    // 핀테크이용번호로 계좌를 조회하는 메서드
    Optional<MockAccount> findByFintechUseNum(String fintechUseNum);
}
