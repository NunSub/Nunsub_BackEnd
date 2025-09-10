package dmu.noonsub_backend.domain.openbanking.dto.mock;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TokenResponse {
    private String accessToken;
    private String tokenType;
    private int expiresIn;
    private String refreshToken;
    private String scope;
    private String userSeqNo;
}
