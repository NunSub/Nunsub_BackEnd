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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_code_std") // 외래 키
    private MockBank bank;

    @Column(unique = true, nullable = false)
    private String fintechUseNum;
    private String accountAlias;
    private String accountNumMasked;
    private String accountHolderName;
    private String accountHolderType;
    private String accountType;
    private String inquiryAgreeYn;
    private String inquiryAgreeDtime;
    private String transferAgreeYn;
    private String transferAgreeDtime;
    private long balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // 외래 키
    private MockUser user;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MockTransaction> transactions = new ArrayList<>();

    public MockAccount(String fintechUseNum, String accountAlias, String bankName, String accountNumMasked, long balance, MockUser user, MockBank bank) {
        this.fintechUseNum = fintechUseNum;
        this.accountAlias = accountAlias;
        this.accountNumMasked = accountNumMasked;
        this.balance = balance;
        this.user = user;
        this.bank = bank;
        this.accountHolderName = user.getUserName();
        this.accountHolderType = "P";
        this.accountType = "1";
        this.inquiryAgreeYn = "Y";
        this.inquiryAgreeDtime = "20240101100000";
        this.transferAgreeYn = "Y";
        this.transferAgreeDtime = "20240101100000";
    }
}