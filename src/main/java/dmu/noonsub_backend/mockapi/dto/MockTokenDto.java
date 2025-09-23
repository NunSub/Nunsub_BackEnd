package dmu.noonsub_backend.mockapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record MockTokenDto(
        @JsonProperty("access_token")
        String accessToken,

        @JsonProperty("token_type")
        String tokenType,

        @JsonProperty("expires_in")
        int expiresIn,

        @JsonProperty("refresh_token")
        String refreshToken,

        String scope,

        @JsonProperty("user_seq_no")
        String userSeqNo
) {

}
