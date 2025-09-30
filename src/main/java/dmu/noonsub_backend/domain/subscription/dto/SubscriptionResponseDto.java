package dmu.noonsub_backend.domain.subscription.dto;

import dmu.noonsub_backend.domain.member.entity.MemberAccounts;
import dmu.noonsub_backend.domain.subscription.entity.Subscription;
import dmu.noonsub_backend.domain.subscription.enums.SubscriptionStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class SubscriptionResponseDto {

    @Getter
    @Builder
    public static class SubscriptionInfo {
        private Long id;
        private String serviceName;
        private Integer paymentAmount;
        private LocalDate nextPaymentDate;
        private SubscriptionStatus status;

        public static SubscriptionInfo fromEntity(Subscription subscription) {
            return SubscriptionInfo.builder()
                    .id(subscription.getId())
                    .serviceName(subscription.getServiceName())
                    .paymentAmount(subscription.getPaymentAmount())
                    .nextPaymentDate(subscription.getNextPaymentDate())
                    .status(subscription.getSubStatus())
                    .build();
        }
    }

    @Getter
    public static class SubscriptionList {
        private final List<SubscriptionInfo> subscriptions;
        private final int totalCount;
        private final int totalAmount;

        public SubscriptionList(List<Subscription> subscriptions) {
            this.subscriptions = subscriptions.stream()
                    .map(SubscriptionInfo::fromEntity)
                    .collect(Collectors.toList());
            this.totalCount = subscriptions.size();
            this.totalAmount = subscriptions.stream()
                    .mapToInt(Subscription::getPaymentAmount)
                    .sum();
        }
    }

    @Getter
    @Builder
    public static class SubscriptionDetail {
        private Long id;
        private String serviceName;
        private Integer paymentAmount;
        private LocalDate nextPaymentDate;
        private SubscriptionStatus status;

        // 계좌 정보
        private String bankName;
        private String accountAlias;
        private String accountNumMasked;

        public static SubscriptionDetail fromEntity(Subscription subscription, MemberAccounts account) {
            return SubscriptionDetail.builder()
                    .id(subscription.getId())
                    .serviceName(subscription.getServiceName())
                    .paymentAmount(subscription.getPaymentAmount())
                    .nextPaymentDate(subscription.getNextPaymentDate())
                    .status(subscription.getSubStatus())
                    .bankName(account != null ? account.getBankName() : null)
                    .accountAlias(account != null ? account.getAccountAlias() : null)
                    .accountNumMasked(account != null ? account.getAccountNumMasked() : null)
                    .build();
        }
    }
}
