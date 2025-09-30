package dmu.noonsub_backend.domain.subscription.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class AddSubscriptionRequestDto {
    private String serviceName;
    private Integer paymentAmount;
    private LocalDate paymentDate;
    private String fintechUseNum; // 어느 계좌에서 나가는지 식별하기 위한 핀테크이용번호
}
