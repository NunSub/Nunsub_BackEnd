package dmu.noonsub_backend.domain.subscription.service;

import dmu.noonsub_backend.domain.common.exception.CustomException;
import dmu.noonsub_backend.domain.common.exception.ErrorCode;
import dmu.noonsub_backend.domain.member.entity.Member;
import dmu.noonsub_backend.domain.member.entity.MemberAccounts;
import dmu.noonsub_backend.domain.member.entity.MemberTransaction;
import dmu.noonsub_backend.domain.member.repository.TransactionRepository;
import dmu.noonsub_backend.domain.member.service.MemberService;
import dmu.noonsub_backend.domain.openbanking.repository.MemberBankRepository;
import dmu.noonsub_backend.domain.subscription.dto.AddSubscriptionRequestDto;
import dmu.noonsub_backend.domain.subscription.dto.SubscriptionResponseDto;
import dmu.noonsub_backend.domain.subscription.entity.Subscription;
import dmu.noonsub_backend.domain.subscription.enums.SubscriptionStatus;
import dmu.noonsub_backend.domain.subscription.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final MemberService memberService;
    private final TransactionRepository transactionRepository;
    private MemberBankRepository memberBankRepository;

    public SubscriptionResponseDto.SubscriptionList getActiveSubscriptions(String phoneNumber) {
        Member member = memberService.getMemberByPhoneNumber(phoneNumber);
        List<Subscription> activeSubscriptions = subscriptionRepository
                .findAllByMemberAndSubStatusOrderByNextPaymentDateAsc(member, SubscriptionStatus.ACTIVE);

        return new SubscriptionResponseDto.SubscriptionList(activeSubscriptions);
    }

    public SubscriptionResponseDto.SubscriptionDetail getSubscriptionDetail(String phoneNumber, Long subscriptionId) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new CustomException(ErrorCode.SUBSCRIPTION_NOT_FOUND));

        if (!subscription.getMember().getCellNo().equals(phoneNumber)) {
            throw new CustomException(ErrorCode.FORBIDDEN);
        }

        MemberAccounts paymentAccount = subscription.getPaymentAccount();

        if (paymentAccount == null) {
            paymentAccount = transactionRepository
                    .findTopBySubscriptionOrderByTranDateDescTranTimeDesc(subscription)
                    .map(MemberTransaction::getMemberAccount)
                    .orElse(null);
        }

        return SubscriptionResponseDto.SubscriptionDetail.fromEntity(subscription, paymentAccount);
    }

    @Transactional
    public void addSubscription(String phoneNumber, AddSubscriptionRequestDto requestDto) {
        Member member = memberService.getMemberByPhoneNumber(phoneNumber);

        // 동일한 이름의 구독이 이미 있는지 확인
        subscriptionRepository.findByMemberAndServiceName(member, requestDto.getServiceName())
                .ifPresent(s -> {
                    throw new CustomException(ErrorCode.SUBSCRIPTION_ALREADY_EXISTS);
                });

        // 출금 계좌 정보 조회 및 확인
        MemberAccounts paymentAccount = memberBankRepository.findByMemberAndFintechUseNum(member, requestDto.getFintechUseNum())
                .orElseThrow(() -> new CustomException(ErrorCode.BANK_ACCOUNT_NOT_FOUND));

        Subscription newSubscription = Subscription.builder()
                .member(member)
                .serviceName(requestDto.getServiceName())
                .paymentAmount(requestDto.getPaymentAmount())
                .nextPaymentDate(requestDto.getPaymentDate())
                .subStatus(SubscriptionStatus.ACTIVE)
                .paymentAccount(paymentAccount) // 결제 계좌 연결
                .build();

        subscriptionRepository.save(newSubscription);
    }

    @Transactional
    public void deactivateSubscription(String phoneNumber, Long subscriptionId) {
        Member member = memberService.getMemberByPhoneNumber(phoneNumber);
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new CustomException(ErrorCode.SUBSCRIPTION_NOT_FOUND));

        // 본인의 구독이 맞는지 확인
        if (!subscription.getMember().equals(member)) {
            throw new CustomException(ErrorCode.FORBIDDEN);
        }

        subscription.deactivate();
        // subscriptionRepository.save(subscription)은 @Transactional에 의해 자동 처리 (Dirty Checking)
    }
}
