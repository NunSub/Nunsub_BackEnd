package dmu.noonsub_backend.mockapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter; // setUser를 위해 추가

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter // setUser를 위해 추가
@NoArgsConstructor // JPA는 기본 생성자가 필요합니다.
@Table(name = "mock_accounts")
public class MockAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String fintechUseNum;
    private String accountAlias;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_code_std") // 외래 키
    private MockBank bank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // 외래 키
    private MockUser user;

    private String accountNum;
    private String accountNumMasked;
    private String accountSeq;
    private String accountHolderName;
    private String accountHolderType;
    private String accountType;
    private String inquiryAgreeYn;
    private String inquiryAgreeDtime;
    private String transferAgreeYn;
    private String transferAgreeDtime;

    private String payerNum;
    private long balance;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MockTransaction> transactions = new ArrayList<>();

}