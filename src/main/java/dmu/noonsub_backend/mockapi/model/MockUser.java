package dmu.noonsub_backend.mockapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor // JPA는 기본 생성자가 필요합니다.
@Table(name = "mock_users") // 테이블명 지정
public class MockUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String userSeqNo;

    @Column(nullable = false)
    private String ci;

    @Column(nullable = false)
    private String userName;

    // User가 삭제될 때 연관된 Account도 함께 삭제되도록 설정
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MockAccount> accounts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MockCardInfo> cardInfos = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MockPayInfo> payInfos = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MockInsuranceInfo> insuranceInfos = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MockLoanInfo> loanInfos = new ArrayList<>();

    public MockUser(String userSeqNo, String ci) {
        this.userSeqNo = userSeqNo;
        this.ci = ci;
    }

    public void addAccount(MockAccount account) {
        this.accounts.add(account);
        account.setUser(this);
    }
}