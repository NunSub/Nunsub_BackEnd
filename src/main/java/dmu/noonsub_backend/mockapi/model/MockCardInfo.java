package dmu.noonsub_backend.mockapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "mock_card_infos")
public class MockCardInfo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bankCodeStd;
    private String memberBankCode;
    private String inquiryAgreeDtime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private MockUser user;

    public MockCardInfo(String bankCodeStd, String memberBankCode, String inquiryAgreeDtime, MockUser user) {
        this.bankCodeStd = bankCodeStd;
        this.memberBankCode = memberBankCode;
        this.inquiryAgreeDtime = inquiryAgreeDtime;
        this.user = user;
    }

}
