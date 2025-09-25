package dmu.noonsub_backend.global.auth.controller;

import dmu.noonsub_backend.domain.member.dto.LoginRequestDto;
import dmu.noonsub_backend.domain.member.dto.SignUpRequestDto;
import dmu.noonsub_backend.domain.member.service.MemberService;
import dmu.noonsub_backend.global.auth.dto.LogoutRequestDto;
import dmu.noonsub_backend.global.auth.dto.MemberCheckRequestDto;
import dmu.noonsub_backend.global.auth.dto.RefreshRequestDto;
import dmu.noonsub_backend.global.auth.dto.TokenBox;
import dmu.noonsub_backend.global.auth.jwt.JwtUtil;
import dmu.noonsub_backend.global.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth (인증 및 회원가입)", description = "사용자 인증, 회원가입, 토큰 관리 API")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;
    private final MemberService memberService;


    @Operation(summary = "회원가입", description = "새로운 사용자를 등록하고, 액세스/리프레시 토큰을 발급합니다.")
    @ApiResponse(responseCode = "200",
            description = "회원가입 성공",
            content = @Content(schema = @Schema(implementation = TokenBox.class)))
    @PostMapping("/register")
    public ResponseEntity<TokenBox> register(@RequestBody SignUpRequestDto signUpRequestDto){
        TokenBox tokenBox = authService.register(signUpRequestDto);
        return ResponseEntity.ok(tokenBox);
    }

    @Operation(summary = "로그인", description = "전화번호와 비밀번호로 로그인하고, 액세스/리프레시 토큰을 발급합니다.")
    @ApiResponse(
            responseCode = "200",
            description = "로그인 성공",
            content = @Content(schema = @Schema(implementation = TokenBox.class)))
    @PostMapping("/login")
    public ResponseEntity<TokenBox> login(@RequestBody LoginRequestDto loginRequestDto){
        TokenBox tokenBox = authService.login(loginRequestDto);
        return ResponseEntity.ok(tokenBox);
    }

    @Operation(summary = "회원 존재 여부 확인", description = "전화번호를 통해 이미 가입된 회원인지 확인합니다.")
    @ApiResponse(
            responseCode = "200",
            description = "조회 성공 (true: 이미 존재, false: 없음)")
    @PostMapping("/check-member")
    public ResponseEntity<Boolean> checkMember(@RequestBody MemberCheckRequestDto memberCheckRequestDto) {

        String phoneNumber = memberCheckRequestDto.getPhoneNumber();
        boolean exists = memberService.existsByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(exists);
    }

    @Operation(summary = "액세스 토큰 재발급", description = "유효한 리프레시 토큰을 사용하여 만료된 액세스 토큰을 재발급합니다.")
    @ApiResponse(
            responseCode = "200",
            description = "토큰 재발급 성공",
            content = @Content(schema = @Schema(implementation = TokenBox.class)))
    @PostMapping("/refresh")
    public ResponseEntity<TokenBox> refresh(@RequestBody RefreshRequestDto refreshRequestDto){
        TokenBox tokenBox = authService.refresh(refreshRequestDto);
        return ResponseEntity.ok(tokenBox);
    }

    @Operation(summary = "로그아웃", description = "사용자를 로그아웃 처리하고, 기기의 리프레시 토큰을 무효화합니다. (Authorization 헤더에 Bearer 토큰 필요)")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, @RequestBody LogoutRequestDto logoutRequestDto){
        String authHeader = request.getHeader("Authorization");
        String accessToken = jwtUtil.parseToken(authHeader);
        String phonNumber = jwtUtil.getPhoneNumberFromToken(accessToken);

        authService.logout(phonNumber, logoutRequestDto.getDeviceId(), accessToken);
        return ResponseEntity.ok().build();
    }
}
