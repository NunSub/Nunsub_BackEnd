package dmu.noonsub_backend.mockapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MockUserInfoResponseDto {

    @JsonProperty("api_tran_id")
    private String apiTranId;

    @JsonProperty("api_tran_dtm")
    private String apiTranDtm;

    @JsonProperty("rsp_code")
    private String rspCode;

    @JsonProperty("rsp_message")
    private String rspMessage;

    @JsonProperty("user_seq_no")
    private String userSeqNo;

    @JsonProperty("user_ci")
    private String userCi;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("user_info")
    private String userInfo;

    @JsonProperty("user_gender")
    private String userGender;

    @JsonProperty("user_cell_no")
    private String userCellNo;

    @JsonProperty("user_email")
    private String userEmail;

    @JsonProperty("res_cnt")
    private String resCnt;

    @JsonProperty("res_list")
    private List<MockAccountDetailDto> resList;

    @JsonProperty("inquiry_card_cnt")
    private String inquiryCardCnt;

    @JsonProperty("inquiry_card_list")
    private List<MockCardInfoDetailDto> inquiryCardList;

    @JsonProperty("inquiry_pay_cnt")
    private String inquiryPayCnt;

    @JsonProperty("inquiry_pay_list")
    private List<MockPayInfoDetailDto> inquiryPayList;

    @JsonProperty("inquiry_insurance_cnt")
    private String inquiryInsuranceCnt;

    @JsonProperty("inquiry_insurance_list")
    private List<MockInsuranceInfoDetailDto> inquiryInsuranceList;

    @JsonProperty("inquiry_loan_cnt")
    private String inquiryLoanCnt;

    @JsonProperty("inquiry_loan_list")
    private List<MockLoanInfoDetailDto> inquiryLoanList;
}
