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
public class WithdrawResponseDto{
    @JsonProperty("api_tran_id")
    private String apiTranId;
    @JsonProperty("rsp_code")
    private String rspCode;
    @JsonProperty("rsp_message")
    private String rspMessage;
    @JsonProperty("fintech_use_num")
    private String fintechUseNum;
    @JsonProperty("tran_amt")
    private String tranAmt;
    @JsonProperty("wd_limit_remain_amt")
    private String wdLimitRemainAmt;
}
