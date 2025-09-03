package dmu.noonsub_backend.global.auth.jwt;

import dmu.noonsub_backend.domain.common.exception.CustomException;
import dmu.noonsub_backend.domain.common.exception.ErrorCode;
import dmu.noonsub_backend.global.auth.dto.TokenBox;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SecurityException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {

    @Value("${jwt.access-token-expiration}")
    private long accessTokenExpiration;

    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    @Value("${jwt.secondary-auth-token-expiration}")
    private long secondaryAuthExpiration;

    private final SecretKey secretKey;

    public JwtUtil(@Value("${jwt.secret}") final String secretKey, final StringRedisTemplate redisTemplate) {
        this.secretKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    // Access, Refresh 토큰 생성
    public TokenBox generateTokenBox(final String phoneNumber, final String authority) {
        String accessToken = generateAccessToken(phoneNumber, authority);
        String refreshToken = generateRefreshToken(phoneNumber);

        return TokenBox.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .authority(authority)
                .build();
    }

    // 2. Access 토큰 생성
    public String generateAccessToken(String phoneNumber, String authority) {
        return Jwts.builder()
                .issuer("dmu-nunsub.or.kr")
                .subject(phoneNumber)
                .claim("auth", authority)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
                .signWith(this.secretKey)
                .compact();
    }

    // 3. Refresh 토큰 생성
    public String generateRefreshToken(String phoneNumber) {
        return Jwts.builder()
                .issuer("dmu-nunsub.or.kr")
                .subject(phoneNumber)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .signWith(this.secretKey)
                .compact();
    }

    public String generateSecondaryAuthToken(String phoneNumber) {
        return Jwts.builder()
                .issuer("dmu-nunsub.or.kr")
                .subject(phoneNumber)
                .claim("scope", "2FA_VERIFIED")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + secondaryAuthExpiration))
                .signWith(this.secretKey)
                .compact();
    }

    // Request 헤더에서 토큰 추출
    public String parseToken(final String authHeaderValue){
        if (authHeaderValue == null || !authHeaderValue.startsWith("Bearer ")) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
        return authHeaderValue.substring(7);
    }

    // 토큰으로부터 Claims 추출
    public Claims parseClaims(final String token){
        try{
            return Jwts.parser()
                    .verifyWith(this.secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (SecurityException | MalformedJwtException |
                 UnsupportedJwtException | IllegalArgumentException | ClaimJwtException e) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
    }

    // 4. Claims에서 권한 정보 추출
    public String getAuthorityFromToken(String token) {
        return parseClaims(token).get("auth", String.class);
    }

    public String getPhoneNumberFromToken(String token) {
        return parseClaims(token).getSubject();
    }

    // 토큰 만료 여부 확인
    public boolean isExpired(final String token) {
        try {
            return parseClaims(token).getExpiration().before(new Date());
        } catch (CustomException e) {
            return ErrorCode.TOKEN_EXPIRED.equals(e.getErrorCode());
        }
    }
}
