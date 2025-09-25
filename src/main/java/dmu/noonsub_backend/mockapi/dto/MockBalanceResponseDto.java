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
public class MockBalanceResponseDto{
    @JsonProperty("api_tran_Id")
    private String apiTranId;
    @JsonProperty("api_tran_dtm")
    private String apiTranDtm;
    @JsonProperty("rsp_code")
    private String rspCode;
    @JsonProperty("rsp_message")
    private String rspMessage;
    @JsonProperty("balance_amt")
    private String balanceAmt;
}