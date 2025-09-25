package dmu.noonsub_backend.domain.openbanking.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TokenExchangeRequestDto {
    private String code;
    private String state;
}
