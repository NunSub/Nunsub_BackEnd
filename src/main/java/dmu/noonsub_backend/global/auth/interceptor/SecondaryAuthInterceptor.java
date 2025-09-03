package dmu.noonsub_backend.global.auth.interceptor;

import dmu.noonsub_backend.domain.common.exception.CustomException;
import dmu.noonsub_backend.domain.common.exception.ErrorCode;
import dmu.noonsub_backend.global.auth.annotation.Requires2FA;
import dmu.noonsub_backend.global.auth.controller.AuthController;
import dmu.noonsub_backend.global.auth.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.objectweb.asm.Handle;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class SecondaryAuthInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;
    private static final String HEADER_2FA_TOKEN = "X-2FA-TOKEN ";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod && ((HandlerMethod) handler).hasMethodAnnotation(Requires2FA.class)) {
            // 1. 2FA 토큰 헤더 존재 여부 확인
            String token = request.getHeader(HEADER_2FA_TOKEN);
            if (token == null || token.isBlank()) {
                throw new CustomException(ErrorCode.MISSING_2FA_TOKEN);
            }

            // 2. 2FA 토큰 유효성 검증
            Claims claims = jwtUtil.parseClaims(token);
            String scope = claims.get("scope", String.class);
            if(!"2FA_VERIFIED".equals(scope)) {
                throw new CustomException(ErrorCode.INVALID_2FA_TOKEN);
            }

            // 3. 로그인한 사용자와 2FA 토큰의 사용자 일치 여부 확인
            String primaryAuthUser = SecurityContextHolder.getContext().getAuthentication().getName();
            String secondaryAuthUser = claims.getSubject();
            if(!primaryAuthUser.equals(secondaryAuthUser)) {
                throw new CustomException(ErrorCode.USER_MISMATCH_2FA);
            }
        }

        return true;
    }
}
