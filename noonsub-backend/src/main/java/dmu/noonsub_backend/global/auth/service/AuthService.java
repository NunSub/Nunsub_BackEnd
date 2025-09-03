package dmu.noonsub_backend.global.auth.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import dmu.noonsub_backend.domain.common.exception.CustomException;
import dmu.noonsub_backend.domain.common.exception.ErrorCode;
import dmu.noonsub_backend.domain.member.dto.LoginRequestDto;
import dmu.noonsub_backend.domain.member.dto.SignUpRequestDto;
import dmu.noonsub_backend.domain.member.entity.Member;
import dmu.noonsub_backend.domain.member.enums.Role;
import dmu.noonsub_backend.domain.member.repository.MemberRepository;
import dmu.noonsub_backend.domain.member.service.MemberService;
import dmu.noonsub_backend.global.auth.dto.RefreshRequestDto;
import dmu.noonsub_backend.global.auth.dto.TokenBox;
import dmu.noonsub_backend.global.auth.dto.TrustedDeviceDto;
import dmu.noonsub_backend.global.auth.entity.TrustedDevice;
import dmu.noonsub_backend.global.auth.jwt.JwtUtil;
import dmu.noonsub_backend.global.auth.repository.TrustedDeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberService memberService;
    private final TrustedDeviceRepository trustedDeviceRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;
    private final StringRedisTemplate redisTemplate;

    private static final String BLACKLIST_PREFIX = "BLACKLIST_";

    @Transactional
    public TokenBox register(SignUpRequestDto signUpRequestDto){
        Member newMember = memberService.signUp(signUpRequestDto);
        return jwtUtil.generateTokenBox(newMember.getPhoneNumber(), newMember.getRole().name());
    }

    @Transactional
    public TokenBox login(LoginRequestDto loginRequestDto){
        // 1. 전화번호로 사용자 조회
        Member member = memberService.getMemberByPhoneNumber(loginRequestDto.getPhoneNumber());

        // 2. 저장된 PIN과 입력된 PIN이 일치하는지 확인
        if (!passwordEncoder.matches(loginRequestDto.getPin(), member.getPin())) {
            throw new CustomException(ErrorCode.PASSWORD_MISMATCH);
        }

        // 2. JWT 토큰 생성
        TokenBox tokenBox = jwtUtil.generateTokenBox(member.getPhoneNumber(), member.getRole().name());

        // 3. AuthService가 직접 Refresh Token을 DB에 저장/업데이트
        TrustedDevice trustedDevice = trustedDeviceRepository.findByMemberAndDeviceId(member, loginRequestDto.getDeviceId())
                .orElse(TrustedDevice.builder()
                        .member(member)
                        .deviceId(loginRequestDto.getDeviceId())
                        .deviceName(loginRequestDto.getDeviceName())
                        .build());

        trustedDevice.updateRefreshToken(tokenBox.getRefreshToken());
        trustedDeviceRepository.save(trustedDevice);

        return tokenBox;
    }

    @Transactional
    public TokenBox refresh(RefreshRequestDto refreshRequestDto) {
        // 1. JwtUtil로 토큰의 서명과 만료 시간 등 기본적인 유효성 검증
        jwtUtil.parseClaims(refreshRequestDto.getRefreshToken());

        // 2. DB에서 해당 리프레시 토큰과 Device ID가 실제로 존재하는지 확인
        TrustedDevice trustedDevice = trustedDeviceRepository
                .findByRefreshTokenAndDeviceId(refreshRequestDto.getRefreshToken(), refreshRequestDto.getDeviceId())
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_TOKEN));

        // 3. 새로운 AccessToken 생성
        Member member = trustedDevice.getMember();
        String newAccessToken = jwtUtil.generateAccessToken(member.getPhoneNumber(), member.getRole().name());

        // 4. 새로운 사용 시간 업데이트
        trustedDevice.updateRefreshToken(trustedDevice.getRefreshToken());
        trustedDeviceRepository.save(trustedDevice);

        // 5. 새 AccessToken과 기존 RefreshToken을 TokenBox로 반환
        return new TokenBox(newAccessToken, refreshRequestDto.getRefreshToken(), member.getRole().name());
    }

    @Transactional
    public void logout(String phoneNumber, String deviceId, String accessToken) {
        // 1. DB에서 Refresh Token 삭제
        Member member = memberService.getMemberByPhoneNumber(phoneNumber);
        TrustedDevice trustedDevice = trustedDeviceRepository.findByMemberAndDeviceId(member, deviceId)
                .orElseThrow(() -> new CustomException(ErrorCode.DEVICE_NOT_FOUND));

        trustedDeviceRepository.delete(trustedDevice);

        // 2. Redis에 Access Token 블랙리스트 추가
        long remainingExpiration = jwtUtil.parseClaims(accessToken).getExpiration().getTime() - System.currentTimeMillis();
        if (remainingExpiration > 0) {
            redisTemplate.opsForValue().set(BLACKLIST_PREFIX + accessToken, "logout", remainingExpiration, TimeUnit.MILLISECONDS);
        }
    }

    public List<TrustedDeviceDto> getTrustedDevices(String phoneNumber) {
        Member member = memberService.getMemberByPhoneNumber(phoneNumber);
        return trustedDeviceRepository.findAllByMember(member).stream()
                .map(TrustedDeviceDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void removeDevice(String phoneNumber, String deviceId) {
        Member member = memberService.getMemberByPhoneNumber(phoneNumber);
        TrustedDevice trustedDevice = trustedDeviceRepository.findByMemberAndDeviceId(member, deviceId)
                .orElseThrow(() -> new CustomException(ErrorCode.DEVICE_NOT_FOUND));
        trustedDeviceRepository.delete(trustedDevice);
    }
}
