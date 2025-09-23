package dmu.noonsub_backend.mockapi.dto;

import lombok.Builder;
import java.util.List;

@Builder
public record MockUserInfoResponseDto(
        String api_tran_id,
        String api_tran_dtm,
        String rsp_code,
        String rsp_message,
        String user_seq_no,
        String user_ci,
        String user_name,
        String res_cnt,
        List<MockAccountDetailDto> res_list,
        String inquiry_card_cnt,
        List<MockCardInfoDetailDto> inquiry_card_list,
        String inquiry_pay_cnt,
        List<MockPayInfoDetailDto> inquiry_pay_list,
        String inquiry_insurance_cnt,
        List<MockInsuranceInfoDetailDto> inquiry_insurance_list,
        String inquiry_loan_cnt,
        List<MockLoanInfoDetailDto> inquiry_loan_list
) {}
