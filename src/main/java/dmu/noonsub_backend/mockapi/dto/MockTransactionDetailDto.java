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
public class MockTransactionDetailDto{
    @JsonProperty("tran_date")
    String tranDate;
    @JsonProperty("tran_time")
    String tranTime;
    @JsonProperty("inout_type")
    String inoutType;
    @JsonProperty("tran_type")
    String tranType;
    @JsonProperty("print_content")
    String printContent;
    @JsonProperty("tran_amt")
    String tranAmt;
    @JsonProperty("after_balance_amt")
    String afterBalanceAmt;
    @JsonProperty("branch_name")
    String branchName;
}
