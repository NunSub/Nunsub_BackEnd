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
public class WithdrawRequestDto{
    @JsonProperty("bank_tran_id")
    private String bankTranId;
    @JsonProperty("fintech_use_num")
    private String fintechUseNum;
    @JsonProperty("tran_amt")
    private String tranAmt;
}