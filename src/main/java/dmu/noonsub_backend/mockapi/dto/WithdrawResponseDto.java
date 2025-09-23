package dmu.noonsub_backend.mockapi.dto;

import lombok.Builder;

@Builder
public record WithdrawResponseDto(
        String api_tran_id,
        String rsp_code,
        String rsp_message,
        String fintech_use_num,
        String tran_amt,
        String wd_limit_remain_amt
) {}
