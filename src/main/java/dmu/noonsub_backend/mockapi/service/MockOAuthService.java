package dmu.noonsub_backend.mockapi.service;

import dmu.noonsub_backend.mockapi.dto.MockRevokeTokenDto;
import dmu.noonsub_backend.mockapi.dto.MockTokenDto;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.util.UUID;

@Service
public class MockOAuthService {

    /**
     * 사용자인증 및 서비스 등록 확인 요청을 처리하는 로직
     * @param redirectUri 콜백 URI
     * @param state 상태 값
     * @return Authorization Code를 포함하여 리다이렉트할 RedirectView 객체
     */
    public RedirectView handleAuthorize(String redirectUri, String state, String scope) {
        // 가짜 Authorization Code 생성
        String authorizationCode = "MOCK_AUTHORIZATION_CODE_" + UUID.randomUUID();

        // 파라미터를 쿼리 스트링으로 추가하여 리다이렉트 URL 생성
        String redirectUrl = String.format("%s?code=%s&state=%s&scope=%s",
                redirectUri, authorizationCode, state, scope);

        return new RedirectView(redirectUrl);
    }

    /**
     * 토큰 발급 및 갱신 로직
     * @param grantType 인증 방식 (authorization_code, refresh_token)
     * @return 명세서와 동일한 구조의 Mock 토큰 DTO
     */
    public MockTokenDto issueMockToken(String grantType) {
        System.out.println("Service Logic - grant_type: " + grantType);

        // 항상 고정된 Mock 토큰 정보를 생성하여 반환
        return MockTokenDto.builder()
                .accessToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMDAwMDAwMTA2Iiwic2NvcGUiOlsibG9naW4iLCJpbnF1aXJ5IiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOiIxNTc2OTgxNDkzIiwianRpIjoiN2M5NTVjYzAtM2I4ZS00ZTJkLTg0YWYtZTcyNWI5YzFlNTAwIn0.DONr1R0Ae2fe4qN56VZkEGB364vo951rUToHkDTxvJ8")
                .tokenType("Bearer")
                .expiresIn(7776000)
                .refreshToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMDAwMDAwMTA2Iiwic2NvcGUiOlsibG9naW4iLCJpbnF1aXJ5IiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOiIxNTc3ODQ1NDkzIiwianRpIjoiMDcyYzhmMDYtMmNkMi00MTE2LTk2ZWMtN2EwNmEyYzE0OWQ2In0._jJcs7roGVkyQ9u3N5XKbz7Ff8n8nzgzGYfKy9hR6rQ")
                .scope("login inquiry transfer")
                .userSeqNo("1000000106")
                .build();
    }

    /**
     * 토큰 폐기 로직
     * @param clientId 이용기관 클라이언트 ID
     * @param accessToken 폐기할 액세스 토큰
     * @return 폐기 성공을 나타내는 응답 DTO
     */
    public MockRevokeTokenDto revokeMockToken(String clientId, String clientSecret, String accessToken) {
        System.out.println("Service Logic - Revoking token: " + accessToken + " for client: " + clientId);

        // 명세서에 정의된 모든 필드를 포함하여 성공 응답 생성
        return MockRevokeTokenDto.builder()
                .rspCode("A0000")
                .rspMessage("처리 성공")
                .clientId(clientId)
                .clientSecret(clientSecret)
                .accessToken(accessToken)
                .refreshToken("MOCK_REFRESH_TOKEN_REVOKED_12345")
                .build();
    }
}
