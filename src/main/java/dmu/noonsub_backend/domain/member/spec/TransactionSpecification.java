package dmu.noonsub_backend.domain.member.spec;

import dmu.noonsub_backend.domain.member.entity.MemberAccounts;
import dmu.noonsub_backend.domain.member.entity.MemberTransaction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.List;

public class TransactionSpecification {

    public static Specification<MemberTransaction> forAccounts(List<MemberAccounts> accounts) {
        return (root, query, criteriaBuilder) -> root.get("memberAccount").in(accounts);
    }

    public static Specification<MemberTransaction> byYearMonth(String yearMonth) { // "yyyy-MM" 형식
        if (!StringUtils.hasText(yearMonth)) {
            return null; // 파라미터가 없으면 조건을 추가하지 않음
        }
        // "yyyy-MM" -> "yyyyMM%"
        String searchPattern = yearMonth.replace("-", "") + "%";
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("tranDate"), searchPattern);
    }

    public static Specification<MemberTransaction> byDate(String date) { // "yyyy-MM-dd" 형식
        if (!StringUtils.hasText(date)) {
            return null; // 파라미터가 없으면 조건을 추가하지 않음
        }
        // "yyyy-MM-dd" -> "yyyyMMdd"
        String searchDate = date.replace("-", "");
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("tranDate"), searchDate);
    }

    public static Specification<MemberTransaction> byInoutType(String inoutType) {
        if(!StringUtils.hasText(inoutType)) {
            return null;
        }
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("inoutType"), inoutType));
    }
}
