package dmu.noonsub_backend.domain.openbanking;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "openbanking")
@Getter @Setter
public class OpenBankingProperties {
    private String authorizeUrl;
    private String tokenUrl;
    private String clientId;
    private String clientSecret;
    private String clientUseCode;
    private String redirectUri;
    private String scope;
    private String baseUrl;
}
