package dmu.noonsub_backend.mockapi.controller;


import dmu.noonsub_backend.mockapi.dto.MockRevokeTokenDto;
import dmu.noonsub_backend.mockapi.dto.MockTokenDto;
import dmu.noonsub_backend.mockapi.service.MockOAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/oauth/2.0")
public class MockOAuthController {
    private final MockOAuthService mockOAuthService;

    // 생성자를 통해 서비스 의존성 주입
    public MockOAuthController(MockOAuthService mockOAuthService) {
        this.mockOAuthService = mockOAuthService;
    }

    @GetMapping("/authorize")
    public RedirectView authorize(
            @RequestParam("redirect_uri") String redirectUri,
            @RequestParam("state") String state,
            @RequestParam("scope") String scope) {
        // 실제 로직은 서비스에 위임
        return mockOAuthService.handleAuthorize(redirectUri, state, scope);
    }

    @PostMapping("/token")
    public ResponseEntity<MockTokenDto> issueToken(
            @RequestParam("grant_type") String grantType,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "refresh_token", required = false) String refreshToken,
            @RequestParam("client_id") String clientId) {
        // 실제 로직은 서비스에 위임
        MockTokenDto mockToken = mockOAuthService.issueMockToken(grantType);
        return ResponseEntity.ok(mockToken);
    }

    @PostMapping("/revoke")
    public ResponseEntity<MockRevokeTokenDto> revokeToken(
            @RequestParam("client_id") String clientId,
            @RequestParam("client_secret") String clientSecret,
            @RequestParam("access_token") String accessToken) {
        // 실제 로직은 서비스에 위임
        MockRevokeTokenDto response = mockOAuthService.revokeMockToken(clientId, clientSecret, accessToken);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/authorize_account")
    public RedirectView authorizeAccount(
            @RequestParam("redirect_uri") String redirectUri,
            @RequestParam("state") String state) {
        // authorize와 로직이 유사하므로 동일한 서비스 메서드 재활용 (필요시 별도 메서드 생성)
        return mockOAuthService.handleAuthorize(redirectUri, state, "login inquiry");
    }
}
