package dmu.noonsub_backend.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    private String phoneNumber;
    private String pin;
    private String deviceId;
    private String deviceName;
}
