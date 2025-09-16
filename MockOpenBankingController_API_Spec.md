# API 명세서: 목업 오픈뱅킹 (Mock Open Banking)

**기본 URL**: `http://localhost:8080/mock/openbanking`

**설명**: 이 컨트롤러는 실제 오픈뱅킹 API를 호출하지 않고, 테스트 및 개발 목적으로 고정된 또는 임의의 데이터를 반환하는 목업(Mock) 엔드포인트입니다.

---

### 1. 목업 토큰 발급

*   **`POST /oauth/2.0/token`**
*   **설명**: 목업 액세스 토큰을 발급합니다.
*   **Query Parameters**
    | 파라미터명 | 타입 | 필수 여부 | 설명 |
    | :--- | :--- | :--- | :--- |
    | `grant_type` | `String` | **Yes** | 토큰 발급 타입 (e.g., `authorization_code`) |
*   **Responses**
    *   **`200 OK`**: 토큰 발급 성공. 실제 오픈뱅킹과 유사한 형식의 목업 토큰 정보를 반환합니다.
        *   **Body**: `TokenResponse`
        | 필드명 | 타입 | 설명 |
        | :--- | :--- | :--- |
        | `access_token` | `String` | 목업 액세스 토큰 |
        | `token_type` | `String` | 토큰 타입 (항상 "Bearer") |
        | `expires_in` | `int` | 토큰 만료 시간(초) |
        | `refresh_token` | `String` | 목업 리프레시 토큰 |
        | `scope` | `String` | 토큰 권한 범위 |
        | `user_seq_no` | `String` | 목업 사용자 일련번호 |
        <br>
        *   **Example**:
            ```json
            {
                "access_token": "mock_access_token_...",
                "token_type": "Bearer",
                "expires_in": 86400,
                "refresh_token": "mock_refresh_token_...",
                "scope": "inquiry login transfer",
                "user_seq_no": "mock_user_seq_no_..."
            }
            ```

---

### 2. 목업 사용자 정보 조회

*   **`GET /v2.0/user/me`**
*   **설명**: 목업 사용자 정보와 계좌 목록을 조회합니다.
*   **Query Parameters**
    | 파라미터명 | 타입 | 필수 여부 | 설명 |
    | :--- | :--- | :--- | :--- |
    | `user_seq_no` | `String` | **Yes** | 조회할 사용자의 목업 일련번호 |
*   **Responses**
    *   **`200 OK`**: 조회 성공. 고정된 사용자 정보와 DB에 저장된 목업 계좌 목록을 반환합니다.
        *   **Body**: `UserInfoResponse`
        | 필드명 | 타입 | 설명 |
        | :--- | :--- | :--- |
        | `api_tran_id` | `String` | 거래 고유 ID |
        | `rsp_code` | `String` | 응답 코드 (A0000: 성공) |
        | `rsp_message` | `String` | 응답 메시지 |
        | `user_seq_no` | `String` | 사용자 일련번호 |
        | `user_info` | `Object` | 사용자 상세 정보 |
        | `res_list` | `Array` | 사용자 계좌 목록 |
        <br>
        *   **Example**:
            ```json
            {
                "api_tran_id": "...",
                "rsp_code": "A0000",
                "rsp_message": "Success",
                "user_seq_no": "...",
                "user_info": {
                    "user_name": "김눈섭",
                    "user_birth": "19950115",
                    "user_gender": "M",
                    "user_nationality": "Y",
                    "user_ci": "01012345678"
                },
                "res_list": [
                    {
                        "fintech_use_num": "...",
                        "account_alias": "...",
                        "bank_code_std": "...",
                        "account_num_masked": "...",
                        "account_type": "입출금",
                        "currency_code": "KRW",
                        "account_reg_dt": null
                    }
                ]
            }
            ```

---

### 3. 목업 계좌 거래내역 조회

*   **`GET /v2.0/account/transaction_list/fin_num`**
*   **설명**: 특정 목업 계좌의 거래내역을 조회합니다.
*   **Query Parameters**
    | 파라미터명 | 타입 | 필수 여부 | 설명 |
    | :--- | :--- | :--- | :--- |
    | `fintech_use_num` | `String` | **Yes** | 거래내역을 조회할 계좌의 핀테크 이용번호 |
*   **Responses**
    *   **`200 OK`**: 조회 성공. DB에 저장된 목업 거래내역을 반환합니다.
        *   **Body**: `TransactionHistoryResponse`
        | 필드명 | 타입 | 설명 |
        | :--- | :--- | :--- |
        | `api_tran_id` | `String` | 거래 고유 ID |
        | `rsp_code` | `String` | 응답 코드 (A0000: 성공) |
        | `rsp_message` | `String` | 응답 메시지 |
        | `bank_name` | `String` | 은행명 |
        | `account_num_masked` | `String` | 마스킹된 계좌번호 |
        | `account_alias` | `String` | 계좌 별칭 |
        | `res_list` | `Array` | 거래내역 목록 |
        <br>
        *   **Example**:
            ```json
            {
                "api_tran_id": "...",
                "rsp_code": "A0000",
                "rsp_message": "Success",
                "bank_name": "눈섭은행",
                "account_num_masked": "111-***-***111",
                "account_alias": "눈섭-급여계좌",
                "res_list": [
                    {
                        "tran_date": "20250911",
                        "tran_time": "113000",
                        "tran_type": "출금",
                        "tran_amt": "50000",
                        "print_content": "점심값",
                        "after_balance_amt": "1000000"
                    }
                ]
            }
            ```
    *   `400 Bad Request`: `fintech_use_num`에 해당하는 계좌를 찾을 수 없는 경우 (e.g., `IllegalArgumentException` 발생)
