package dmu.noonsub_backend.domain.openbanking.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenExchangeRequestDto {
    private String code;
    private String code_verifier;
    private String state;
}
