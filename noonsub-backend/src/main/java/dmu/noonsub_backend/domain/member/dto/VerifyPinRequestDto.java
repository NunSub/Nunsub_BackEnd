package dmu.noonsub_backend.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyPinRequestDto {
    @NotBlank(message = "PIN은 필수 입니다.")
    private String pin;
}
