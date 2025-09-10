package dmu.noonsub_backend.domain.openbanking.entity.mock;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mock_accounts")
@Getter
@Setter
public class MockAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String fintechUseNum;

    @Column(nullable = false)
    private String accountAlias;

    @Column(nullable = false)
    private String bankCodeStd;

    @Column(nullable = false)
    private String bankName;

    @Column(nullable = false)
    private String accountNumMasked;
}
