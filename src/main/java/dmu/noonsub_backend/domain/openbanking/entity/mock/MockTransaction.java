package dmu.noonsub_backend.domain.openbanking.entity.mock;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "mock_transactions")
@Getter
@Setter
public class MockTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fintechUseNum;

    @Column(nullable = false)
    private LocalDate tranDate;

    @Column(nullable = false)
    private LocalTime tranTime;

    @Column(nullable = false)
    private String tranType; // "IN" or "OUT"

    @Column(nullable = false)
    private Long tranAmt;

    @Column(nullable = false)
    private String printContent;

    @Column(nullable = false)
    private Long afterBalanceAmt;
}
