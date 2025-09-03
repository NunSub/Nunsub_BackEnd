package dmu.noonsub_backend.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class PasswordRequest {
    @NotBlank(message = "전화번호는 필수 입니다.")
    @Pattern(regexp = "^010[0-9]{8}$", message = "전화번호는 -을 제외한 010으로 시작하는 11자리 숫자여야 합니다.")
    private String phoneNumber;

    @NotBlank(message = "비밀번호는 필수 입니다.")
    @Pattern(regexp = "^[0-9]{6}$", message = "비밀번호는 6자리 숫자여야 합니다.")
    private String password;
}
