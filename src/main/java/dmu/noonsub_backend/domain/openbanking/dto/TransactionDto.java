package dmu.noonsub_backend.domain.openbanking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class TransactionDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private String bankName;
        private String accountAlias;
        private String accountNumMasked;
        private List<Transaction> resList;

        private int pageNumber;       // 현재 페이지 번호
        private int pageSize;         // 페이지 크기
        private int totalPages;       // 전체 페이지 수
        private long totalElements;   // 전체 항목 수
        private boolean last;         // 마지막 페이지 여부

        private String balanceAmt;
        private String nextPageYn;
        private String beforeInquiryTraceInfo;
    }

    @Getter
    @NoArgsConstructor
    public static class OpenBankingResponse {
        @JsonProperty("api_tran_id")
        private String apiTranId;
        @JsonProperty("rsp_code")
        private String rspCode;
        @JsonProperty("bank_name")
        private String bankName;
        @JsonProperty("balance_amt")
        private String balanceAmt;
        @JsonProperty("page_record_cnt")
        private String pageRecordCnt;
        @JsonProperty("next_page_yn")
        private String nextPageYn;
        @JsonProperty("before_inquiry_trace_info")
        private String beforeInquiryTraceInfo;
        @JsonProperty("account_alias")
        private String accountAlias;
        @JsonProperty("account_num_masked")
        private String accountNumMasked;
        @JsonProperty("res_list")
        private List<Transaction> resList;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
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
