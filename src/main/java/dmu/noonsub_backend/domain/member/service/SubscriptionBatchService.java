package dmu.noonsub_backend.domain.member.service;

import dmu.noonsub_backend.domain.member.entity.Member;
import dmu.noonsub_backend.domain.member.entity.MemberAccounts;
import dmu.noonsub_backend.domain.member.entity.MemberTransaction;
import dmu.noonsub_backend.domain.member.repository.MemberRepository;
import dmu.noonsub_backend.domain.member.repository.TransactionRepository;
import dmu.noonsub_backend.domain.openbanking.dto.SyncCompletedEvent;
import dmu.noonsub_backend.domain.subscription.entity.Subscription;
import dmu.noonsub_backend.domain.subscription.enums.SubscriptionStatus;
import dmu.noonsub_backend.domain.subscription.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionBatchService {

    private final TransactionRepository transactionRepository;
    private final MemberRepository memberRepository;
    private final SubscriptionRepository subscriptionRepository;

    /**
     * [스케줄링용] 매일 새벽 2시에 모든 사용자에 대해 주기성 분석을 요청합니다.
     */
    @Scheduled(initialDelay = 300000, fixedRate = 86400000)
    public void analyzeAllUsers() {
        log.info("[BATCH] 전체 사용자 주기성 결제 분석 배치 작업을 시작합니다.");
        List<Member> allMembers = memberRepository.findAll();
        for (Member member : allMembers) {
            detectRecurringPaymentsForUser(member);
        }
        log.info("[BATCH] 전체 사용자 주기성 결제 분석 작업을 모두 요청했습니다.");
    }

    @TransactionalEventListener
    @Async // 분석 자체는 비동기로 처리
    @Transactional(propagation = Propagation.REQUIRES_NEW) // 새로운 트랜잭션에서 실행
    public void handleSyncCompleted(SyncCompletedEvent event) {
        Member member = event.getMember();
        log.info(">> SyncCompletedEvent 수신. 사용자 ID {} 에 대한 주기성 분석을 시작합니다.", member.getId());
        detectRecurringPaymentsForUser(member);
    }

    @Async
    @Transactional
    public void detectRecurringPaymentsForUser(Member member) {
        log.info("[ASYNC] 사용자 ID {}의 주기성 결제 분석을 시작합니다.", member.getId());

        LocalDate today = LocalDate.now();
        LocalDate fourMonthsAgo = today.minusMonths(4);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String fromDate = fourMonthsAgo.format(dateFormatter);
        String toDate = today.format(dateFormatter);

        List<MemberTransaction> candidates = transactionRepository.findByMemberAccount_MemberAndTranDateBetween(member, fromDate, toDate);

        Map<String, List<MemberTransaction>> groupedByContent = candidates.stream()
                .filter(tx -> tx.getPrintContent() != null && !tx.getPrintContent().isBlank())
                .collect(Collectors.groupingBy(MemberTransaction::getPrintContent));

        for (Map.Entry<String, List<MemberTransaction>> entry : groupedByContent.entrySet()) {
            List<MemberTransaction> transactions = entry.getValue();
            String serviceName = entry.getKey();

            if ( transactions.size() < 3) {
                continue; // 최소 3회 이상 거래된 내역만 분석
            }

            transactions.sort(Comparator.comparing(tx -> tx.getTranDate() + tx.getTranTime()));
            if (isRecurring(transactions)) {
                log.info("[ASYNC] 정기 결제 패턴 발견. 사용자 ID: {}, 내용: '{}'", member.getId(), serviceName);

                MemberAccounts detectedAccount = transactions.get(transactions.size() - 1).getMemberAccount();

                Optional<Subscription> optionalSubscription = subscriptionRepository.findByMemberAndServiceName(member, serviceName);

                Subscription subscription;
                if (optionalSubscription.isPresent()) {
                    subscription = optionalSubscription.get();
                    log.info("기존 구독 정보 발견. ID: {}", subscription.getId());

                    long averageAmount = calculateAverageAmount(transactions);
                    LocalDate nextPaymentDate = calculateNextPaymentDate(transactions);

                    subscription.reactivate(nextPaymentDate, (int) averageAmount);
                    subscription.updatePaymentAccount(detectedAccount);
                } else {
                    log.info("신규 구독 정보 생성.");
                    long averageAmount = calculateAverageAmount(transactions);
                    LocalDate nextPaymentDate = calculateNextPaymentDate(transactions);

                    subscription = Subscription.builder()
                            .member(member)
                            .serviceName(serviceName)
                            .paymentAmount((int) averageAmount)
                            .nextPaymentDate(nextPaymentDate)
                            .subStatus(SubscriptionStatus.ACTIVE)
                            .paymentAccount(detectedAccount)
                            .build();
                }

                Subscription savedSubscription = subscriptionRepository.save(subscription);

                // 4. 이 패턴에 해당하는 모든 거래 내역에 구독 정보(FK)를 연결
                transactions.forEach(tx -> {
                    tx.setSubscription(savedSubscription);
                    // tx.setCategory(Category.SUBSCRIPTION); // 카테고리도 변경할 수 있음 (선택사항)
                });
                transactionRepository.saveAll(transactions);
            }
        }
        log.info("[ASYNC] 사용자 ID {}의 주기성 결제 분석을 종료합니다.", member.getId());
    }

    private boolean isRecurring(List<MemberTransaction> transactions) {
        long averageAmount = calculateAverageAmount(transactions);
        if (averageAmount == 0) return false;

        double amountThreshold = averageAmount * 0.15;

        boolean amountIsStable = transactions.stream()
                .allMatch(tx -> Math.abs(tx.getTranAmt() - averageAmount) <= amountThreshold);

        if (!amountIsStable) {
            return false;
        }

        List<Long> intervals = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        for (int i = 0; i < transactions.size() - 1; i++) {
            LocalDate date1 = LocalDate.parse(transactions.get(i).getTranDate(), dateFormatter);
            LocalDate date2 = LocalDate.parse(transactions.get(i + 1).getTranDate(), dateFormatter);
            intervals.add(ChronoUnit.DAYS.between(date1, date2));
        }

        return intervals.stream().allMatch(interval -> interval >= 25 && interval <= 35);
    }

    // 편의 메소드 추가
    private long calculateAverageAmount(List<MemberTransaction> transactions) {
        if (transactions == null || transactions.isEmpty()) {
            return 0;
        }
        return (long) transactions.stream()
                .mapToLong(MemberTransaction::getTranAmt)
                .average()
                .orElse(0.0);
    }

    private LocalDate calculateNextPaymentDate(List<MemberTransaction> transactions) {
        if (transactions == null || transactions.isEmpty()) {
            return LocalDate.now().plusMonths(1);
        }
        // 가장 최근 거래일 기준 한달 뒤로 설정
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate lastTransactionDate = LocalDate.parse(transactions.get(transactions.size() - 1).getTranDate(), dateFormatter);
        return lastTransactionDate.plusMonths(1);
    }
}
