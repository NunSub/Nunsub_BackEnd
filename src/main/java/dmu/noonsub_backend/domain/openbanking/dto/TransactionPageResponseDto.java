package dmu.noonsub_backend.domain.openbanking.dto;

import dmu.noonsub_backend.domain.common.dto.PageResponseDto;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class TransactionPageResponseDto extends PageResponseDto<TransactionDto.Transaction> {

    private final long totalDeposit;
    private final long totalWithdrawal;

    public TransactionPageResponseDto(Page<TransactionDto.Transaction> page, long totalDeposit, long totalWithdrawal) {
        super(page);
        this.totalDeposit = totalDeposit;
        this.totalWithdrawal = totalWithdrawal;
    }
}
