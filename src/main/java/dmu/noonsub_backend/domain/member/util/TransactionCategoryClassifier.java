package dmu.noonsub_backend.domain.member.util;

import dmu.noonsub_backend.domain.member.enums.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class TransactionCategoryClassifier {

    private static final Logger log = LoggerFactory.getLogger(TransactionCategoryClassifier.class);

    // EnumMap을 사용하여 더 효율적이고 타입-세이프한 맵을 사용
    private static final Map<Category, List<String>> CATEGORY_KEYWORDS_MAP = new EnumMap<>(Category.class);

    static {
        // 각 카테고리별로 키워드 리스트를 관리
        CATEGORY_KEYWORDS_MAP.put(Category.FOOD, Arrays.asList(
                "맥도날드", "버거킹", "롯데리아", "kfc", "맘스터치", "써브웨이",
                "배달의민족", "요기요", "쿠팡이츠", "땡겨요",
                "아웃백", "빕스", "애슐리",
                "김밥천국", "한솥", "본도시락", "죽이야기", "본죽",
                "교촌치킨", "bhc", "bbq", "굽네", "푸라닭",
                "도미노피자", "미스터피자", "피자헛", "파파존스",
                "중국집", "짜장", "짬뽕", "마라탕", "떡볶이", "순대", "튀김",
                "식당", "음식점", "푸드코트", "구내식당"
        ));

        CATEGORY_KEYWORDS_MAP.put(Category.CAFE, Arrays.asList(
                "스타벅스", "스벅", "starbucks",
                "투썸", "투썸플레이스",
                "이디야", "ediya",
                "폴바셋", "paulbassett",
                "메가커피", "mega coffee",
                "컴포즈", "compose",
                "빽다방",
                "커피", "카페", "cafe"
        ));

        CATEGORY_KEYWORDS_MAP.put(Category.SHOPPING, Arrays.asList(
                "이마트", "홈플러스", "롯데마트", "코스트코", "트레이더스",
                "쿠팡", "네이버페이", "11번가", "지마켓", "옥션", "ssg", "롯데온",
                "올리브영", "롭스", "랄라블라", "시코르",
                "다이소", "무신사", "지그재그", "에이블리", "브랜디",
                "백화점", "아울렛", "면세점",
                "마켓컬리", "오아시스"
        ));

        CATEGORY_KEYWORDS_MAP.put(Category.CONVENIENCE_STORE, Arrays.asList(
                "gs25", "cu", "씨유", "세븐일레븐", "7-eleven", "이마트24", "emart24", "미니스톱", "ministop",
                "지에스리테일", "비지에프리테일"
        ));

        CATEGORY_KEYWORDS_MAP.put(Category.TRANSPORT, Arrays.asList(
                "버스", "지하철", "택시", "카카오택시", "카카오t", "티머니", "캐시비",
                "코레일", "srt", "ktx", "기차", "철도",
                "항공", "항공권", "대한항공", "아시아나", "제주항공", "진에어", "티웨이",
                "주유", "충전소", "하이패스", "고속도로"
        ));

        CATEGORY_KEYWORDS_MAP.put(Category.HEALTH, Arrays.asList(
                "병원", "의원", "치과", "한의원", "보건소", "약국", "동물병원"
        ));

        CATEGORY_KEYWORDS_MAP.put(Category.ENTERTAINMENT, Arrays.asList(
                "cgv", "롯데시네마", "메가박스", "영화", "연극", "뮤지컬", "콘서트",
                "pc방", "피시방", "노래방", "코인노래", "당구장", "볼링장",
                "게임", "결제" // "게임" 키워드 추가
        ));

        CATEGORY_KEYWORDS_MAP.put(Category.EDUCATION, Arrays.asList(
                "학원", "인강", "클래스101", "패스트캠퍼스", "인프런",
                "교보문고", "영풍문고", "알라딘", "yes24", "서점"
        ));

        CATEGORY_KEYWORDS_MAP.put(Category.UTILITIES, Arrays.asList(
                "관리비", "아파트아이", "가스요금", "전기요금", "수도요금", "공과금"
        ));

        CATEGORY_KEYWORDS_MAP.put(Category.TELECOM, Arrays.asList(
                "skt", "kt", "lgu+", "sk텔레콤", "케이티", "엘지유플러스",
                "통신요금", "휴대폰", "인터넷", "알뜰폰"
        ));

        CATEGORY_KEYWORDS_MAP.put(Category.PERSONAL_CARE, Arrays.asList(
                "미용실", "헤어", "네일", "피부과", "마사지", "사우나"
        ));

        CATEGORY_KEYWORDS_MAP.put(Category.TRAVEL, Arrays.asList(
                "호텔", "숙소", "숙박", "야놀자", "여기어때", "에어비앤비", "부킹닷컴", "아고다"
        ));

        // 구독 카테고리는 SubscriptionBatchService에서 별도로 처리하므로 여기서 제외하거나,
        // 초기 분류를 위해 남겨둘 수 있습니다. 우선은 남겨두겠습니다.
        CATEGORY_KEYWORDS_MAP.put(Category.SUBSCRIPTION, Arrays.asList(
                "netflix", "넷플릭스", "youtube", "유튜브", "멜론", "왓챠", "watcha", "디즈니플러스", "tving", "티빙", "wavve", "웨이브", "정기결제"
        ));
    }

    /**
     * 거래내역의 printContent를 기반으로 카테고리를 결정합니다.
     * @param printContent 거래내역 내용
     * @return 분류된 카테고리. 매칭되는 규칙이 없으면 ETC를 반환합니다.
     */
    public static Category determineCategory(String printContent) {
        if (printContent == null || printContent.isBlank()) {
            return Category.ETC;
        }

        // 1. 전각 -> 반각 변환
        String contentAfterWidthConversion = convertFullWidthToHalfWidth(printContent);

        // 정규화 및 소문자 변환은 한 번만 수행
        String normalizedContent = contentAfterWidthConversion.toLowerCase().replaceAll("\\s+", "");

        log.info("===== 분류 시작: 원본='{}', 정규화='{}' =====", printContent, normalizedContent);

        // 카테고리 맵을 순회하며 키워드 포함 여부 확인
        for (Map.Entry<Category, List<String>> entry : CATEGORY_KEYWORDS_MAP.entrySet()) {
            Category category = entry.getKey();
            List<String> keywords = entry.getValue();

            for (String keyword : keywords) {
                boolean isMatch = normalizedContent.contains(keyword);

                if (isMatch) {
                    log.info("===== 분류 성공: 카테고리='{}', 키워드='{}' =====", category, keyword);
                    return category;
                }
            }
        }

        log.info("===== 분류 실패: 매칭되는 키워드 없음 =====");
        return Category.ETC; // 매칭되는 키워드가 없으면 '기타'로 분류
    }

    private static String convertFullWidthToHalfWidth(String fullWidthStr) {
        if (fullWidthStr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : fullWidthStr.toCharArray()) {
            // 전각 영문/기호 (U+FF01-FF5E)를 반각으로 변환
            if (c >= '\uFF01' && c <= '\uFF5E') {
                sb.append((char) (c - 0xFEE0));
            }
            // 전각 공백 (U+3000)을 반각 공백으로 변환
            else if (c == '\u3000') {
                sb.append(' ');
            }
            // 그 외 문자는 그대로 추가
            else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
