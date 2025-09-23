package dmu.noonsub_backend.mockapi.dto;

import lombok.Builder;

@Builder
public record MockTransactionDetailDto(
        String tran_date,
        String tran_time,
        String inout_type,
        String tran_type,
        String print_content,
        String tran_amt,
        String after_balance_amt,
        String branch_name
) {}
