package dmu.noonsub_backend.mockapi.dto;

import lombok.Builder;

@Builder
public record MockLoanInfoDetailDto(
        String bank_code_std,
        String inquiry_agree_dtime
) {}
