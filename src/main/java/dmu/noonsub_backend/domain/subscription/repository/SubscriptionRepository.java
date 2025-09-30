package dmu.noonsub_backend.domain.subscription.repository;

import dmu.noonsub_backend.domain.member.entity.Member;
import dmu.noonsub_backend.domain.subscription.entity.Subscription;
import dmu.noonsub_backend.domain.subscription.enums.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    Optional<Subscription> findByMemberAndServiceName(Member member, String serviceName);

    List<Subscription> findAllByMemberAndSubStatusOrderByNextPaymentDateAsc(Member member, SubscriptionStatus status);
}
