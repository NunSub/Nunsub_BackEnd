package dmu.noonsub_backend.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetupPinRequestDto {
    @NotBlank(message = "PIN은 필수 입니다.")
    @Pattern(regexp = "^[0-9]{6}$", message = "PIN은 6자리 숫자여야 합니다.")
    private String pin;
}
