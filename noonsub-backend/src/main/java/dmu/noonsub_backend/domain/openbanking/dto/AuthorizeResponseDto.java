package dmu.noonsub_backend.domain.openbanking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthorizeResponseDto {
    private String authorizeUrl;
}
