package dmu.noonsub_backend.global.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCheckRequestDto {
    private String name;
    private String residentNumber;
    private String phoneNumber;
}
