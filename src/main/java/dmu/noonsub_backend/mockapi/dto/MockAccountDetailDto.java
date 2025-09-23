package dmu.noonsub_backend.mockapi.dto;

import lombok.Builder;

@Builder
public record MockAccountDetailDto(
        String fintech_use_num,
        String account_alias,
        String bank_code_std,
        String bank_code_sub,
        String bank_name,
        String account_num_masked,
        String account_holder_name,
        String account_holder_type,
        String account_type,
        String inquiry_agree_yn,
        String inquiry_agree_dtime,
        String transfer_agree_yn,
        String transfer_agree_dtime
) { }
