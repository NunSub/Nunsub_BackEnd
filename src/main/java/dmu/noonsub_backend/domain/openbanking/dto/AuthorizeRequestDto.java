package dmu.noonsub_backend.domain.openbanking.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthorizeRequestDto {
    private String code_challenge;
    private String returnDeepLink;
    private String cove_verifier;
}
