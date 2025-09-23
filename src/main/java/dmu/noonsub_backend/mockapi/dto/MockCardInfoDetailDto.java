package dmu.noonsub_backend.mockapi.dto;

import lombok.Builder;

@Builder
public record MockCardInfoDetailDto(
        String bank_code_std,
        String member_bank_code,
        String inquiry_agree_dtime
) {}
