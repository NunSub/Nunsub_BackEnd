package dmu.noonsub_backend.mockapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "mock_insurance_infos")
public class MockInsuranceInfo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bankCodeStd;
    private String inquiryAgreeDtime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private MockUser user;

    public MockInsuranceInfo(String bankCodeStd, String inquiryAgreeDtime, MockUser user) {
        this.bankCodeStd = bankCodeStd;
        this.inquiryAgreeDtime = inquiryAgreeDtime;
        this.user = user;
    }
}
