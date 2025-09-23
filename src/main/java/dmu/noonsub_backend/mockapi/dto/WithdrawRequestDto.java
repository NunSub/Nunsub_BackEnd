package dmu.noonsub_backend.mockapi.dto;

public record WithdrawRequestDto(
        String bank_tran_id,
        String fintech_use_num,
        String tran_amt
) {}