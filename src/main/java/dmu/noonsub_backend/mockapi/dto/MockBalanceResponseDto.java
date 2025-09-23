package dmu.noonsub_backend.mockapi.dto;

import lombok.Builder;

@Builder
public record MockBalanceResponseDto(
        String api_tran_id,
        String api_tran_dtm,
        String rsp_code,
        String rsp_message,
        String balance_amt
) {}