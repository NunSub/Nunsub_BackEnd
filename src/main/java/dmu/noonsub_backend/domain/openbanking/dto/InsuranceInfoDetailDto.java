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
public class InsuranceInfoDetailDto {
    @JsonProperty("bank_code_std")
    private String bankCodeStd;

    @JsonProperty("inquiry_agree_dtime")
    private String inquiryAgreeDtime;
}
