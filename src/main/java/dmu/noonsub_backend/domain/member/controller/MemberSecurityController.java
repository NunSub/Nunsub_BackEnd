package dmu.noonsub_backend.domain.member.controller;

import dmu.noonsub_backend.domain.member.dto.BiometricStatusRequestDto;
import dmu.noonsub_backend.domain.member.dto.SetupPinRequestDto;
import dmu.noonsub_backend.domain.member.dto.VerifyPinRequestDto;
import dmu.noonsub_backend.domain.member.service.MemberService;
import dmu.noonsub_backend.global.auth.dto.TrustedDeviceDto;
import dmu.noonsub_backend.global.auth.service.AuthService;
import dmu.noonsub_backend.global.userdetails.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/members/security")
@RequiredArgsConstructor
public class MemberSecurityController {

    private final MemberService memberService;
    private final AuthService authService;

    @PostMapping("/pin")
    public ResponseEntity<Void> setupPin(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                         @Valid @RequestBody SetupPinRequestDto requestDto){
        memberService.setupPin(userDetails.getUsername(), requestDto.getPin());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/pin/verify")
    public ResponseEntity<Map<String, String>> verifyPin(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                         @Valid @RequestBody VerifyPinRequestDto requestDto){
        String secondaryAuthToken = memberService.verifyPin(userDetails.getUsername(), requestDto.getPin());
        return ResponseEntity.ok().body(Map.of("secondaryAuthToken", secondaryAuthToken));
    }

    @GetMapping("/devices")
    public ResponseEntity<List<TrustedDeviceDto>> getTrustedDevices(@AuthenticationPrincipal UserDetailsImpl userDetails){
        List<TrustedDeviceDto> devices = authService.getTrustedDevices(userDetails.getUsername());
        return ResponseEntity.ok(devices);
    }

    @DeleteMapping("/devices/{deviceId}")
    public ResponseEntity<Void> removeDevice(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                             @PathVariable String deviceId){
        authService.removeDevice(userDetails.getUsername(), deviceId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/biometrics")
    public ResponseEntity<Void> setBiometricStatus(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                   @RequestBody BiometricStatusRequestDto requestDto) {
        memberService.setBiometricStatus(userDetails.getUsername(), requestDto.isEnabled());
        return ResponseEntity.ok().build();
    }
}
