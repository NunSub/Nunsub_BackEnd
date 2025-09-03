package dmu.noonsub_backend.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto {

    @NotBlank(message = "이름은 필수 입력 입니다.")
    private String name;

    @NotBlank(message = "주민등록번호는 필수 입니다.")
    @Pattern(regexp = "^[0-9]{7}$", message = "주민번호 앞 7자리를 입력하세요.")
    private String residentNumber;

    @NotBlank(message = "전화번호는 필수 입니다.")
    @Pattern(regexp = "^010[0-9]{8}$", message = "전화번호는 -을 제외한 010으로 시작하는 11자리 숫자여야 합니다.")
    private String phoneNumber;

    private String ci;

    @NotBlank(message = "통신사는 필수 입니다.")
    private String mobileCarrier;

    @NotBlank(message = "PIN은 필수 입니다.")
    @Pattern(regexp = "^[0-9]{6}$", message = "PIN은 6자리 숫자여야 합니다.")
    private String pin;
}
