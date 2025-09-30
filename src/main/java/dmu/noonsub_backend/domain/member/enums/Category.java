package dmu.noonsub_backend.domain.member.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    FOOD("음식"),
    TRANSPORT("교통"),
    SHOPPING("쇼핑"),
    HEALTH("건강"),
    ENTERTAINMENT("엔터테인먼트"),
    EDUCATION("교육"),
    UTILITIES("공과금"),
    PERSONAL_CARE("개인관리"),
    TRAVEL("여행"),
    GIFTS_DONATIONS("선물/기부"),
    INVESTMENTS("투자"),
    ETC("기타");

    private final String description;

    @JsonCreator
    public static Category fromString(String description) {
        if(description == null) {
            return ETC;
        }
        for (Category category : Category.values()) {
            if (category.description.equalsIgnoreCase(description)) {
                return category;
            }
        }
        return ETC;
    }
}
