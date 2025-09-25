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
public class AccountDetailDto {

    @JsonProperty("fintech_use_num")
    private String fintechUseNum;

    @JsonProperty("account_alias")
    private String accountAlias;

    @JsonProperty("bank_code_std")
    private String bankCodeStd;

    @JsonProperty("bank_code_sub")
    private String bankCodeSub;

    @JsonProperty("bank_name")
    private String bankName;

    @JsonProperty("savings_bank_name")
    private String savingsBankName;

    @JsonProperty("account_num")
    private String accountNum;

    @JsonProperty("account_num_masked")
    private String accountNumMasked;

    @JsonProperty("account_seq")
    private String accountSeq;

    @JsonProperty("account_holder_name")
    private String accountHolderName;

    @JsonProperty("account_holder_type")
    private String accountHolderType;

    @JsonProperty("account_type")
    private String accountType;

    @JsonProperty("inquiry_agree_yn")
    private String inquiryAgreeYn;

    @JsonProperty("inquiry_agree_dtime")
    private String inquiryAgreeDtime;

    @JsonProperty("transfer_agree_yn")
    private String transferAgreeYn;

    @JsonProperty("transfer_agree_dtime")
    private String transferAgreeDtime;

    @JsonProperty("payer_num")
    private String payerNum;
}
