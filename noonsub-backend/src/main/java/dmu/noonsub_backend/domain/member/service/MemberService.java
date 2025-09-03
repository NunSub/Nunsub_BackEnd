package dmu.noonsub_backend.domain.member.service;

import dmu.noonsub_backend.domain.common.exception.CustomException;
import dmu.noonsub_backend.domain.common.exception.ErrorCode;
import dmu.noonsub_backend.domain.member.dto.SignUpRequestDto;
import dmu.noonsub_backend.domain.member.entity.Member;
import dmu.noonsub_backend.domain.member.enums.Role;
import dmu.noonsub_backend.domain.member.repository.MemberRepository;
import dmu.noonsub_backend.global.auth.jwt.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public boolean existsByCi(String ci){
        return memberRepository.findByCi(ci).isPresent();
    }

    // 전화번호로 사용자 조회
    public Member getMemberByPhoneNumber(String phoneNumber) {
        return memberRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
    }

    // 회원 존재 여부 확인
    public boolean existsByPhoneNumber(String phoneNumber) {
        return memberRepository.findByPhoneNumber(phoneNumber).isPresent();
    }

    @Transactional
    public Member signUp(SignUpRequestDto signUpRequestDto){
        // 1. 이미 가입된 회원인지 확인
        if (signUpRequestDto.getCi() != null && !signUpRequestDto.getCi().isBlank()){
            throw new CustomException(ErrorCode.DUPLICATE_MEMBER);
        }

        if (existsByPhoneNumber(signUpRequestDto.getPhoneNumber())){
            throw new CustomException(ErrorCode.DUPLICATE_MEMBER);
        }

        String encodedPin = passwordEncoder.encode(signUpRequestDto.getPin());

        // 3. Member 엔티티 생성 및 저장(권한은 USER 기본 세팅 예시)
        Member member = Member.builder()
                .name(signUpRequestDto.getName())
                .residentNumber(signUpRequestDto.getResidentNumber())
                .phoneNumber(signUpRequestDto.getPhoneNumber())
                .ci(signUpRequestDto.getCi())
                .mobileCarrier(signUpRequestDto.getMobileCarrier())
                .pin(encodedPin)
                .role(Role.USER)
                .build();
        return memberRepository.save(member);
    }

    @Transactional
    public void setupPin(String phoneNumber, String pin) {
        Member member = getMemberByPhoneNumber(phoneNumber);
        member.setPin(passwordEncoder.encode(pin));
        memberRepository.save(member);
    }

    public String verifyPin(String phoneNumber, String pin) {
        Member member = getMemberByPhoneNumber(phoneNumber);
        if (member.getPin() == null){
            throw new CustomException(ErrorCode.PIN_NOT_SET);
        }
        if (!passwordEncoder.matches(pin, member.getPin())) {
            throw new CustomException(ErrorCode.PASSWORD_MISMATCH);
        }
        return jwtUtil.generateSecondaryAuthToken(phoneNumber);
    }

    @Transactional
    public void setBiometricStatus(String phoneNumber, boolean enabled) {
        Member member = getMemberByPhoneNumber(phoneNumber);
        member.setBiometricEnabled(enabled);
        memberRepository.save(member);
    }
}

