package dmu.noonsub_backend.domain.member.util;

import dmu.noonsub_backend.domain.member.enums.Category;

import java.util.HashMap;
import java.util.Map;

public class TransactionCategoryClassifier {

    private static final Map<String, Category> KEYWORD_CATEGORY_MAP = new HashMap<>();

    static {
        // 음식 (FOOD)
        KEYWORD_CATEGORY_MAP.put("스타벅스", Category.FOOD);
        KEYWORD_CATEGORY_MAP.put("투썸", Category.FOOD);
        KEYWORD_CATEGORY_MAP.put("이디야", Category.FOOD);
        KEYWORD_CATEGORY_MAP.put("폴바셋", Category.FOOD);
        KEYWORD_CATEGORY_MAP.put("커피", Category.FOOD);
        KEYWORD_CATEGORY_MAP.put("카페", Category.FOOD);
        KEYWORD_CATEGORY_MAP.put("맥도날드", Category.FOOD);
        KEYWORD_CATEGORY_MAP.put("버거킹", Category.FOOD);
        KEYWORD_CATEGORY_MAP.put("롯데리아", Category.FOOD);
        KEYWORD_CATEGORY_MAP.put("식당", Category.FOOD);
        KEYWORD_CATEGORY_MAP.put("음식점", Category.FOOD);
        KEYWORD_CATEGORY_MAP.put("배달의민족", Category.FOOD);
        KEYWORD_CATEGORY_MAP.put("요기요", Category.FOOD);

        // 쇼핑 (SHOPPING)
        KEYWORD_CATEGORY_MAP.put("GS25", Category.SHOPPING);
        KEYWORD_CATEGORY_MAP.put("CU", Category.SHOPPING);
        KEYWORD_CATEGORY_MAP.put("세븐일레븐", Category.SHOPPING);
        KEYWORD_CATEGORY_MAP.put("이마트24", Category.SHOPPING);
        KEYWORD_CATEGORY_MAP.put("편의점", Category.SHOPPING);
        KEYWORD_CATEGORY_MAP.put("이마트", Category.SHOPPING);
        KEYWORD_CATEGORY_MAP.put("홈플러스", Category.SHOPPING);
        KEYWORD_CATEGORY_MAP.put("롯데마트", Category.SHOPPING);
        KEYWORD_CATEGORY_MAP.put("코스트코", Category.SHOPPING);
        KEYWORD_CATEGORY_MAP.put("하나로마트", Category.SHOPPING);
        KEYWORD_CATEGORY_MAP.put("마트", Category.SHOPPING);
        KEYWORD_CATEGORY_MAP.put("쿠팡", Category.SHOPPING);
        KEYWORD_CATEGORY_MAP.put("네이버페이", Category.SHOPPING);
        KEYWORD_CATEGORY_MAP.put("11번가", Category.SHOPPING);
        KEYWORD_CATEGORY_MAP.put("G마켓", Category.SHOPPING);
        KEYWORD_CATEGORY_MAP.put("옥션", Category.SHOPPING);
        KEYWORD_CATEGORY_MAP.put("온라인", Category.SHOPPING);
        KEYWORD_CATEGORY_MAP.put("올리브영", Category.SHOPPING);
        KEYWORD_CATEGORY_MAP.put("다이소", Category.SHOPPING);

        // 교통 (TRANSPORT)
        KEYWORD_CATEGORY_MAP.put("버스", Category.TRANSPORT);
        KEYWORD_CATEGORY_MAP.put("지하철", Category.TRANSPORT);
        KEYWORD_CATEGORY_MAP.put("택시", Category.TRANSPORT);
        KEYWORD_CATEGORY_MAP.put("코레일", Category.TRANSPORT);
        KEYWORD_CATEGORY_MAP.put("SRT", Category.TRANSPORT);
        KEYWORD_CATEGORY_MAP.put("카카오택시", Category.TRANSPORT);
        KEYWORD_CATEGORY_MAP.put("티머니", Category.TRANSPORT);

        // 건강 (HEALTH)
        KEYWORD_CATEGORY_MAP.put("병원", Category.HEALTH);
        KEYWORD_CATEGORY_MAP.put("약국", Category.HEALTH);
        KEYWORD_CATEGORY_MAP.put("의원", Category.HEALTH);

        // 엔터테인먼트 (ENTERTAINMENT)
        KEYWORD_CATEGORY_MAP.put("넷플릭스", Category.ENTERTAINMENT);
        KEYWORD_CATEGORY_MAP.put("유튜브", Category.ENTERTAINMENT);
        KEYWORD_CATEGORY_MAP.put("멜론", Category.ENTERTAINMENT);
        KEYWORD_CATEGORY_MAP.put("지니", Category.ENTERTAINMENT);
        KEYWORD_CATEGORY_MAP.put("왓챠", Category.ENTERTAINMENT);
        KEYWORD_CATEGORY_MAP.put("디즈니", Category.ENTERTAINMENT);
        KEYWORD_CATEGORY_MAP.put("CGV", Category.ENTERTAINMENT);
        KEYWORD_CATEGORY_MAP.put("롯데시네마", Category.ENTERTAINMENT);
        KEYWORD_CATEGORY_MAP.put("메가박스", Category.ENTERTAINMENT);
        KEYWORD_CATEGORY_MAP.put("영화", Category.ENTERTAINMENT);

        // 교육 (EDUCATION)
        KEYWORD_CATEGORY_MAP.put("학원", Category.EDUCATION);
        KEYWORD_CATEGORY_MAP.put("인강", Category.EDUCATION);
        KEYWORD_CATEGORY_MAP.put("교보문고", Category.EDUCATION);
        KEYWORD_CATEGORY_MAP.put("서점", Category.EDUCATION);

        // 공과금 (UTILITIES)
        KEYWORD_CATEGORY_MAP.put("관리비", Category.UTILITIES);
        KEYWORD_CATEGORY_MAP.put("가스", Category.UTILITIES);
        KEYWORD_CATEGORY_MAP.put("전기", Category.UTILITIES);
        KEYWORD_CATEGORY_MAP.put("수도", Category.UTILITIES);
        KEYWORD_CATEGORY_MAP.put("통신", Category.UTILITIES);

        // 개인관리 (PERSONAL_CARE)
        KEYWORD_CATEGORY_MAP.put("미용실", Category.PERSONAL_CARE);
        KEYWORD_CATEGORY_MAP.put("헤어", Category.PERSONAL_CARE);
        KEYWORD_CATEGORY_MAP.put("네일", Category.PERSONAL_CARE);

        // 여행 (TRAVEL)
        KEYWORD_CATEGORY_MAP.put("호텔", Category.TRAVEL);
        KEYWORD_CATEGORY_MAP.put("숙박", Category.TRAVEL);
        KEYWORD_CATEGORY_MAP.put("항공", Category.TRAVEL);
        KEYWORD_CATEGORY_MAP.put("야놀자", Category.TRAVEL);
        KEYWORD_CATEGORY_MAP.put("여기어때", Category.TRAVEL);

        // 선물/기부 (GIFTS_DONATIONS)
        KEYWORD_CATEGORY_MAP.put("선물", Category.GIFTS_DONATIONS);
        KEYWORD_CATEGORY_MAP.put("기부", Category.GIFTS_DONATIONS);

        // 투자 (INVESTMENTS)
        KEYWORD_CATEGORY_MAP.put("증권", Category.INVESTMENTS);
        KEYWORD_CATEGORY_MAP.put("주식", Category.INVESTMENTS);
    }

    /**
     * 거래내역의 printContent를 기반으로 카테고리를 결정합니다.
     * @param printContent 거래내역 내용
     * @return 분류된 카테고리. 매칭되는 규칙이 없으면 ETC를 반환합니다.
     */
    public static Category determineCategory(String printContent) {
        if (printContent == null){
            return Category.ETC;
        }
        for (Map.Entry<String, Category> entry : KEYWORD_CATEGORY_MAP.entrySet()) {
            if (printContent.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return Category.ETC; // 기본값
    }

}
