package dmu.noonsub_backend.global.auth.controller;

import dmu.noonsub_backend.domain.member.dto.LoginRequestDto;
import dmu.noonsub_backend.domain.member.dto.SignUpRequestDto;
import dmu.noonsub_backend.domain.member.service.MemberService;
import dmu.noonsub_backend.global.auth.dto.LogoutRequestDto;
import dmu.noonsub_backend.global.auth.dto.RefreshRequestDto;
import dmu.noonsub_backend.global.auth.dto.TokenBox;
import dmu.noonsub_backend.global.auth.jwt.JwtUtil;
import dmu.noonsub_backend.global.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;
    private final MemberService memberService;


    @PostMapping("/register")
    public ResponseEntity<TokenBox> register(@RequestBody SignUpRequestDto signUpRequestDto){
        TokenBox tokenBox = authService.register(signUpRequestDto);
        return ResponseEntity.ok(tokenBox);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenBox> login(@RequestBody LoginRequestDto loginRequestDto){
        TokenBox tokenBox = authService.login(loginRequestDto);
        return ResponseEntity.ok(tokenBox);
    }

    @GetMapping("/check-member")
    public ResponseEntity<Map<String, Boolean>> checkMember(@RequestParam String phoneNumber) {
        boolean exists = memberService.existsByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(Map.of("exists", exists));
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenBox> refresh(@RequestBody RefreshRequestDto refreshRequestDto){
        TokenBox tokenBox = authService.refresh(refreshRequestDto);
        return ResponseEntity.ok(tokenBox);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, @RequestBody LogoutRequestDto logoutRequestDto){
        String authHeader = request.getHeader("Authorization");
        String accessToken = jwtUtil.parseToken(authHeader);
        String phonNumber = jwtUtil.getPhoneNumberFromToken(accessToken);

        authService.logout(phonNumber, logoutRequestDto.getDeviceId(), accessToken);
        return ResponseEntity.ok().build();
    }
}
