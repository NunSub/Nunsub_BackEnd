# API 명세서: 오픈뱅킹 (Open Banking)

**기본 URL**: `http://localhost:8080/api/bank`

**공통 사항**:
*   **인증**: 아래의 모든 엔드포인트는 **액세스 토큰(Access Token)을 이용한 인증이 필요**합니다.
*   **요청 헤더**:
    | Name | Value | 필수 여부 | 설명 |
    | :--- | :--- | :--- | :--- |
    | `Authorization` | `Bearer {AccessToken}` | **Yes** | 로그인 시 발급받은 액세스 토큰 |

---

### 1. 계좌 인증 URL 요청

*   **`POST /authorize`**
*   **설명**: 사용자의 계좌를 앱에 연결하기 위해, 오픈뱅킹 인증 페이지로 리디렉션할 URL을 요청합니다.
*   **Request Body** (`Content-Type: application/json`)
    | 필드명 | 타입 | 설명 |
    | :--- | :--- | :--- |
    | `code_challenge` | `String` | PKCE(Proof Key for Code Exchange)를 위한 코드 챌린지 값 |
    | `returnDeepLink` | `String` | 인증 완료 후 돌아올 앱의 딥링크 주소 |
*   **Responses**
    *   **`200 OK`**: URL 요청 성공. 응답 바디에 인증 URL 포함.
        *   **Body**: `AuthorizeResponseDto`
            ```json
            {
              "authorizeUrl": "string"
            }
            ```
    *   `401 Unauthorized`: 인증 실패

---

### 2. 토큰 교환 및 저장

*   **`POST /exchange`**
*   **설명**: 오픈뱅킹 인증 후 받은 임시 코드(code)를 서버로 보내 실제 토큰으로 교환하고, 사용자 계좌 정보를 서버에 저장합니다.
*   **Request Body** (`Content-Type: application/json`)
    | 필드명 | 타입 | 설명 |
    | :--- | :--- | :--- |
    | `code` | `String` | 오픈뱅킹 인증 후 리디렉션 시 받은 임시 코드 |
    | `code_verifier` | `String` | PKCE를 위한 코드 검증 값 |
    | `state` | `String` | CSRF 방지를 위해 `/authorize` 요청 시 보냈던 `state` 값 |
*   **Responses**
    *   `200 OK`: 토큰 교환 및 계좌 정보 저장 성공
    *   `401 Unauthorized`: 인증 실패
    *   `500 Internal Server Error`: 오픈뱅킹 API 통신 중 에러 발생

---

### 3. 계좌 거래 내역 조회

*   **`GET /transactions`**
*   **설명**: 특정 계좌의 거래 내역을 조회합니다.
*   **Query Parameters**
    | 파라미터명 | 타입 | 필수 여부 | 설명 |
    | :--- | :--- | :--- | :--- |
    | `fintech_use_num` | `String` | **Yes** | 조회할 계좌의 핀테크 이용번호 |
    | `from_date` | `String` | **Yes** | 조회 시작일 (형식: `yyyyMMdd`) |
    | `to_date` | `String` | **Yes** | 조회 종료일 (형식: `yyyyMMdd`) |
    | `sort_order` | `String` | No | 정렬 순서. `D`: 최신순, `A`: 과거순 (기본값: `D`) |
*   **Responses**
    *   **`200 OK`**: 조회 성공. 응답 바디에 거래 내역 포함.
        *   **Body**: `TransactionHistoryDto.Response`
        | 필드명 | 타입 | 설명 |
        | :--- | :--- | :--- |
        | `BankName` | `String` | 은행명 |
        | `accountAlias` | `String` | 계좌 별칭 |
        | `accountNumMasked` | `String` | 마스킹된 계좌번호 |
        | `resList` | `Array` | 거래내역 목록 |
        <br>
        *   **`resList` 내부 객체**:
        | 필드명 | 타입 | 설명 |
        | :--- | :--- | :--- |
        | `tran_date` | `String` | 거래일자 |
        | `tran_time` | `String` | 거래시각 |
        | `inout_type` | `String` | 입출금 구분 |
        | `tran_type` | `String` | 거래 종류 |
        | `print_content` | `String` | 거래 내용 |
        | `tran_amt` | `String` | 거래 금액 |
        | `after_balance_amt` | `String` | 거래 후 잔액 |
        | `branch_name` | `String` | 거래 지점명 |
    *   `401 Unauthorized`: 인증 실패
    *   `500 Internal Server Error`: 오픈뱅킹 API 통신 중 에러 발생

---

### 4. 계좌 연결 해제

*   **`POST /unlink`**
*   **설명**: 앱에 연결된 오픈뱅킹 계좌를 해제합니다.
*   **Request Body** (`Content-Type: application/json`)
    | 필드명 | 타입 | 설명 |
    | :--- | :--- | :--- |
    | `fintechUseNum` | `String` | 연결 해제할 계좌의 핀테크 이용번호 |
*   **Responses**
    *   `200 OK`: 계좌 연결 해제 성공
    *   `401 Unauthorized`: 인증 실패
    *   `500 Internal Server Error`: 오픈뱅킹 API 통신 중 에러 발생
