package dmu.noonsub_backend.global.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import dmu.noonsub_backend.domain.common.exception.CustomException;
import dmu.noonsub_backend.domain.common.exception.ErrorCode;
import dmu.noonsub_backend.domain.common.exception.ErrorResponse;
import dmu.noonsub_backend.global.auth.jwt.JwtUtil;
import dmu.noonsub_backend.global.userdetails.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final String HEADER_PREFIX = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String BLACKLIST_PREFIX = "BLACKLIST_";

    private final JwtUtil jwtUtil;
    private final StringRedisTemplate redisTemplate;

    public JwtFilter(JwtUtil jwtUtil, StringRedisTemplate redisTemplate) {
        this.jwtUtil = jwtUtil;
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().startsWith("/v2.0/")) {
            filterChain.doFilter(request, response);
            return;
        }
        final String authHeaderValue = request.getHeader(HEADER_PREFIX);

        if (authHeaderValue == null || !authHeaderValue.startsWith(TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authHeaderValue.substring(7);

        if (redisTemplate.hasKey(BLACKLIST_PREFIX + token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new ObjectMapper()
                    .writeValueAsString(ErrorResponse.of(ErrorCode.INVALID_TOKEN, "로그아웃된 토큰입니다.")));
            return;
        }

        try {
            // 토큰 유효성 검증 및 SecurityContext에 인증 정보 저장
            jwtUtil.parseClaims(token);
            String phoneNumber = jwtUtil.getPhoneNumberFromToken(token);
            String authority = jwtUtil.getAuthorityFromToken(token);

            // UserDetailsImpl를 사용하도록 수정
            UserDetailsImpl userDetails = new UserDetailsImpl(phoneNumber, null, Collections.singletonList(new SimpleGrantedAuthority(authority)));
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (CustomException e) {
            return ;
        }

        filterChain.doFilter(request, response);
    }
}
