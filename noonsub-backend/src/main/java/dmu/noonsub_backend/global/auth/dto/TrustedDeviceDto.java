package dmu.noonsub_backend.global.auth.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dmu.noonsub_backend.global.auth.entity.TrustedDevice;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TrustedDeviceDto {
    private final String deviceId;
    private final String deviceName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime lastUsedAt;

    public TrustedDeviceDto(TrustedDevice trustedDevice) {
        this.deviceId = trustedDevice.getDeviceId();
        this.deviceName = trustedDevice.getDeviceName();
        this.lastUsedAt = trustedDevice.getLastUsedAt();
    }
}
