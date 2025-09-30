package dmu.noonsub_backend.domain.subscription.controller;

import dmu.noonsub_backend.domain.subscription.dto.AddSubscriptionRequestDto;
import dmu.noonsub_backend.domain.subscription.dto.SubscriptionResponseDto;
import dmu.noonsub_backend.domain.subscription.service.SubscriptionService;
import dmu.noonsub_backend.global.userdetails.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping
    public ResponseEntity<SubscriptionResponseDto.SubscriptionList> getMySubscriptions(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        String phoneNumber = userDetails.getUsername();
        SubscriptionResponseDto.SubscriptionList activeSubscriptions = subscriptionService.getActiveSubscriptions(phoneNumber);

        return ResponseEntity.ok(activeSubscriptions);
    }

    @GetMapping("/{subscriptionId}")
    public ResponseEntity<SubscriptionResponseDto.SubscriptionDetail> getSubscriptionDetail(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long subscriptionId
    ) {
        // 1. 현재 로그인한 사용자의 정보와 PathVariable로 받은 구독 ID를 서비스로 전달
        String phoneNumber = userDetails.getUsername();
        SubscriptionResponseDto.SubscriptionDetail subscriptionDetail =
                subscriptionService.getSubscriptionDetail(phoneNumber, subscriptionId);

        // 2. 조회된 결과를 ResponseEntity에 담아 반환
        return ResponseEntity.ok(subscriptionDetail);
    }

    @PostMapping
    public ResponseEntity<Void> addSubscription(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody AddSubscriptionRequestDto requestDto
    ) {
        subscriptionService.addSubscription(userDetails.getUsername(), requestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{subscriptionId}")
    public ResponseEntity<Void> deactivateSubscription(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long subscriptionId
    ) {
        subscriptionService.deactivateSubscription(userDetails.getUsername(), subscriptionId);
        return ResponseEntity.ok().build();
    }
}
