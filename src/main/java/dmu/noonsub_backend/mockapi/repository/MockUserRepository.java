package dmu.noonsub_backend.mockapi.repository;

import dmu.noonsub_backend.mockapi.model.MockUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MockUserRepository extends JpaRepository<MockUser, Long> {
    Optional<MockUser> findByUserSeqNo(String userSeqNo);
    Optional<MockUser> findByUserCellNo(String userCellNo);
}
