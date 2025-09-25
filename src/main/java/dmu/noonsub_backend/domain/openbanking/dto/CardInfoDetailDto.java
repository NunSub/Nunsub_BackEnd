package dmu.noonsub_backend.domain.openbanking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CardInfoDetailDto {

    @JsonProperty("bank_code_std")
    private String bankCodeStd;

    @JsonProperty("member_bank_code")
    private String memberBankCode;

    @JsonProperty("inquiry_agree_dtime")
    private String inquiryAgreeDtime;

}
