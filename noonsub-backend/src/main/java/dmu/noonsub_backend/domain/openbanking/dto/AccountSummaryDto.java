package dmu.noonsub_backend.domain.openbanking.dto;

import dmu.noonsub_backend.domain.openbanking.entity.MemberBank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class AccountSummaryDto {
    private final String bankCode;
    private final String fintechUseNum;

    public AccountSummaryDto(MemberBank memberBank) {
        this.bankCode = memberBank.getBankCode();
        this.fintechUseNum = memberBank.getFintechUseNum();
    }
}
