package dmu.noonsub_backend.mockapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "banks")
public class MockBank {

    @Id
    @Column(name = "bank_code_std") // 은행코드를 기본 키로 사용
    private String bankCodeStd;

    @Column(nullable = false)
    private String bankName;

    public MockBank(String bankCodeStd, String bankName) {
        this.bankCodeStd = bankCodeStd;
        this.bankName = bankName;
    }
}
