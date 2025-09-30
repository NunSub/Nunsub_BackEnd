package dmu.noonsub_backend.domain.openbanking.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TransactionCategoryUpdateRequestDto {
    private String category;
}
