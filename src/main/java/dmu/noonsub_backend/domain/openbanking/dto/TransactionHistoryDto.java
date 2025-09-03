package dmu.noonsub_backend.domain.openbanking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class TransactionHistoryDto {

    @Getter
    @AllArgsConstructor
    public static class Response {
        private String BankName;
        private String accountAlias;
        private String accountNumMasked;
        private List<Transaction> resList;
    }

    @Getter
    @NoArgsConstructor
    public static class OpenBankingResponse {
        @JsonProperty("bank_name")
        private String BankName;
        @JsonProperty("account_alias")
        private String accountAlias;
        @JsonProperty("account_num_masked")
        private String accountNumMasked;
        @JsonProperty("res_list")
        private List<Transaction> resList;
    }

    @Getter
    @NoArgsConstructor
    public static class Transaction {
        @JsonProperty("tran_date")
        private String tranDate;
        @JsonProperty("tran_time")
        private String tranTime;
        @JsonProperty("inout_type")
        private String inoutType;
        @JsonProperty("tran_type")
        private String tranType;
        @JsonProperty("print_content")
        private String printContent;
        @JsonProperty("tran_amt")
        private String tranAmt;
        @JsonProperty("after_balance_amt")
        private String afterBalanceAmt;
        @JsonProperty("branch_name")
        private String branchName;
    }
}
