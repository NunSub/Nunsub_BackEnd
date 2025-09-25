package dmu.noonsub_backend.mockapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MockInsuranceInfoDetailDto{
    @JsonProperty("bank_code_std")
    private String bankCodeStd;

    @JsonProperty("inquiry_agree_dtime")
    private String inquiryAgreeDtime;

}
