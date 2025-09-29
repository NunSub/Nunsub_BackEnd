package dmu.noonsub_backend.mockapi.service;

import dmu.noonsub_backend.mockapi.dto.MockRevokeTokenDto;
import dmu.noonsub_backend.mockapi.dto.MockTokenDto;
import dmu.noonsub_backend.mockapi.model.MockUser;
import dmu.noonsub_backend.mockapi.repository.MockUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class MockOAuthService {

    private final MockUserRepository mockUserRepository;
    private final Map<String, String> authorizationCodes = new ConcurrentHashMap<>();

    /**
     * 사용자인증 및 서비스 등록 확인 요청을 처리하는 로직
     * @param redirectUri 콜백 URI
     * @param state 상태 값
     * @return Authorization Code를 포함하여 리다이렉트할 RedirectView 객체
     */
    public RedirectView handleAuthorize(String redirectUri, String state, String scope, String userCellNo) {
        MockUser user = mockUserRepository.findByUserCellNo(userCellNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 번호의 사용자를 찾을 수 없습니다: " + userCellNo));
        String userSeqNo = user.getUserSeqNo();

        // 가짜 Authorization Code 생성
        String authorizationCode = "MOCK_AUTHORIZATION_CODE_" + UUID.randomUUID();
        authorizationCodes.put(authorizationCode, userSeqNo);

        // 파라미터를 쿼리 스트링으로 추가하여 리다이렉트 URL 생성
        String redirectUrl = String.format("%s?code=%s&state=%s&scope=%s&user_cell_no=%s",
                redirectUri, authorizationCode, state, scope, userCellNo);

        return new RedirectView(redirectUrl);
    }

    /**
     * 토큰 발급 및 갱신 로직
     * @param grantType 인증 방식 (authorization_code, refresh_token)
     * @return 명세서와 동일한 구조의 Mock 토큰 DTO
     */
    public MockTokenDto issueMockToken(String grantType, String codeOrRefreshToken) {
        System.out.println("Service Logic - grant_type: " + grantType);

        String userSeqNo;

        if ("authorization_code".equals(grantType)) {
            if (codeOrRefreshToken == null || !authorizationCodes.containsKey(codeOrRefreshToken)) {
                throw new IllegalArgumentException("유효하지 않은 인증 코드입니다.");
            }
            // 코드를 사용하여 userSeqNo를 조회하고, 사용된 코드는 즉시 삭제
            userSeqNo = authorizationCodes.remove(codeOrRefreshToken);
        } else if ("refresh_token".equals(grantType)) {
            // 리프레시 토큰 로직 (필요 시 구현)
            // 여기서는 임시로 첫 번째 사용자의 번호를 사용
            if(codeOrRefreshToken == null || !codeOrRefreshToken.startsWith("MOCK_REFRESH_TOKEN_FOR_USER_")){
                throw new IllegalArgumentException("유효하지 않은 리프레시 토큰입니다.");
            }
            userSeqNo = codeOrRefreshToken.replace("MOCK_REFRESH_TOKEN_FOR_USER_", "");
            mockUserRepository.findByUserSeqNo(userSeqNo)
                    .orElseThrow(() -> new IllegalStateException("리프레시 토큰에 해당하는 사용자가 없습니다."));
        } else {
            throw new IllegalArgumentException("지원하지 않는 grant_type 입니다.");
        }

        String mockRefreshToken = "MOCK_REFRESH_TOKEN_FOR_USER_" + userSeqNo;

        return MockTokenDto.builder()
                .accessToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMDAwMDAwMTA2Iiwic2NvcGUiOlsibG9naW4iLCJpbnF1aXJ5IiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOiIxNTc2OTgxNDkzIiwianRpIjoiN2M5NTVjYzAtM2I4ZS00ZTJkLTg0YWYtZTcyNWI5YzFlNTAwIn0.DONr1R0Ae2fe4qN56VZkEGB364vo951rUToHkDTxvJ8")
                .tokenType("Bearer")
                .expiresIn(7776000)
                .refreshToken(mockRefreshToken)
                .scope("login inquiry transfer")
                .userSeqNo(userSeqNo)
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
