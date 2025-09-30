package dmu.noonsub_backend.domain.member.repository;

import dmu.noonsub_backend.domain.member.entity.MemberTransaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor
public class TransactionRepositoryImpl implements TransactionRepositoryCustom {
    private final EntityManager em;

    @Override
    public long calculateTotalAmount(Specification<MemberTransaction> spec) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<MemberTransaction> root = query.from(MemberTransaction.class);

        if (spec != null) {
            Predicate predicate = spec.toPredicate(root, query, builder);
            query.where(predicate);
        }

        query.select(builder.sum(root.get("tranAmt")));

        Long result = em.createQuery(query).getSingleResult();
        return result != null ? result : 0L;
    }
}
