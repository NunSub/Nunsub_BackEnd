package dmu.noonsub_backend.domain.member.repository;

import dmu.noonsub_backend.domain.member.entity.MemberTransaction;
import org.springframework.data.jpa.domain.Specification;

public interface TransactionRepositoryCustom {
    long calculateTotalAmount(Specification<MemberTransaction> spec);
}
