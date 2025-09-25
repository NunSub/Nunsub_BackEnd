package dmu.noonsub_backend.domain.member.controller;

import dmu.noonsub_backend.domain.member.dto.BiometricStatusRequestDto;
import dmu.noonsub_backend.domain.member.dto.SetupPinRequestDto;
import dmu.noonsub_backend.domain.member.dto.VerifyPinRequestDto;
import dmu.noonsub_backend.domain.member.service.MemberService;
import dmu.noonsub_backend.global.auth.dto.TrustedDeviceDto;
import dmu.noonsub_backend.global.auth.service.AuthService;
import dmu.noonsub_backend.global.userdetails.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Member Security (사용자 보안 설정)", description = "PIN, 신뢰 기기, 생체 인증 등 사용자 보안 관련 API")
@RestController
@RequestMapping("/api/members/security")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class MemberSecurityController {

    private final MemberService memberService;
    private final AuthService authService;

    @Operation(summary = "PIN 번호 설정", description = "사용자의 6자리 간편 비밀번호(PIN)를 설정합니다.")
    @PostMapping("/pin")
    public ResponseEntity<Void> setupPin(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid @RequestBody SetupPinRequestDto requestDto){
        memberService.setupPin(userDetails.getUsername(), requestDto.getPin());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "PIN 번호 검증 및 2차 인증 토큰 발급", description = "PIN 번호를 검증하고, 성공시 유효시간이 짧은 2차 인증 토큰을 발급합니다.")
    @PostMapping("/pin/verify")
    public ResponseEntity<Map<String, String>> verifyPin(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid @RequestBody VerifyPinRequestDto requestDto){
        String secondaryAuthToken = memberService.verifyPin(userDetails.getUsername(), requestDto.getPin());
        return ResponseEntity.ok().body(Map.of("secondaryAuthToken", secondaryAuthToken));
    }

    @Operation(summary = "신뢰 기기 목록 조회", description = "사용자 계정에 등록된 신뢰 기기(로그인된 기기) 목록을 조회합니다.")
    @GetMapping("/devices")
    public ResponseEntity<List<TrustedDeviceDto>> getTrustedDevices(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserDetailsImpl userDetails){
        List<TrustedDeviceDto> devices = authService.getTrustedDevices(userDetails.getUsername());
        return ResponseEntity.ok(devices);
    }

    @Operation(summary = "신뢰 기기 삭제", description = "특정 기기를 신뢰 기기 목록에서 삭제합니다. (해당 기기에서 로그아웃 처리)")
    @DeleteMapping("/devices/{deviceId}")
    public ResponseEntity<Void> removeDevice(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable String deviceId){
        authService.removeDevice(userDetails.getUsername(), deviceId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "생체 인증 사용 여부 설정", description = "해당 기기에서의 생체 인증 사용 여부를 설정합니다.")
    @PostMapping("/biometrics")
    public ResponseEntity<Void> setBiometricStatus(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody BiometricStatusRequestDto requestDto) {
        memberService.setBiometricStatus(userDetails.getUsername(), requestDto.isEnabled());
        return ResponseEntity.ok().build();
    }
}
