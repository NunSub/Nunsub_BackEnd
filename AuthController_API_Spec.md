# API 명세서: 인증 (Authentication)

**기본 URL**: `http://localhost:8080/api/auth`

**설명**: 사용자 회원가입, 로그인, 로그아웃 및 토큰 관리를 담당하는 엔드포인트입니다.

---

### 1. 회원가입

*   **`POST /register`**
*   **설명**: 새로운 사용자를 등록하고, 성공 시 토큰(Access/Refresh)을 발급합니다.
*   **Request Body** (`Content-Type: application/json`)
    | 필드명 | 타입 | 제약 조건 | 설명 |
    | :--- | :--- | :--- | :--- |
    | `name` | `String` | `NotBlank` | 사용자 이름 |
    | `residentNumber` | `String` | `NotBlank`, 7자리 | 주민등록번호 앞 7자리 |
    | `phoneNumber` | `String` | `NotBlank`, 010으로 시작하는 11자리 | 휴대폰 번호 (`-` 제외) |
    | `ci` | `String` | | 본인인증 후 받은 CI 값 |
    | `mobileCarrier` | `String` | `NotBlank` | 통신사 |
    | `pin` | `String` | `NotBlank`, 6자리 숫자 | 2단계 인증에 사용할 6자리 PIN 번호 |
*   **Responses**
    *   **`200 OK`**: 회원가입 성공. 응답 바디에 토큰 정보 포함.
        *   **Body**: `TokenBox`
            ```json
            {
              "accessToken": "string",
              "refreshToken": "string",
              "authority": "string"
            }
            ```
    *   `400 Bad Request`: 요청 필드의 제약조건이 맞지 않음
    *   `409 Conflict`: 이미 가입된 전화번호 (구현에 따라 다를 수 있음)

---

### 2. 로그인

*   **`POST /login`**
*   **설명**: 전화번호와 PIN으로 로그인하고, 성공 시 토큰(Access/Refresh)을 발급합니다.
*   **Request Body** (`Content-Type: application/json`)
    | 필드명 | 타입 | 설명 |
    | :--- | :--- | :--- |
    | `phoneNumber` | `String` | 가입된 휴대폰 번호 |
    | `pin` | `String` | 설정한 6자리 PIN 번호 |
    | `deviceId` | `String` | 기기 고유 식별자 |
    | `deviceName` | `String` | 기기 이름 (e.g., "My Galaxy S23") |
*   **Responses**
    *   **`200 OK`**: 로그인 성공. 응답 바디에 토큰 정보 포함.
        *   **Body**: `TokenBox` (회원가입 응답과 동일)
    *   `401 Unauthorized`: 로그인 정보(전화번호 또는 PIN)가 일치하지 않음

---

### 3. 회원 존재 여부 확인

*   **`GET /check-member`**
*   **설명**: 전화번호로 가입된 사용자인지 확인합니다.
*   **Query Parameters**
    | 파라미터명 | 타입 | 필수 여부 | 설명 |
    | :--- | :--- | :--- | :--- |
    | `phoneNumber` | `String` | **Yes** | 확인할 휴대폰 번호 |
*   **Responses**
    *   **`200 OK`**: 조회 성공.
        ```json
        {
          "exists": boolean
        }
        ```

---

### 4. 토큰 재발급

*   **`POST /refresh`**
*   **설명**: 유효한 Refresh Token을 사용하여 새로운 Access Token과 Refresh Token을 재발급받습니다.
*   **Request Body** (`Content-Type: application/json`)
    | 필드명 | 타입 | 설명 |
    | :--- | :--- | :--- |
    | `refreshToken` | `String` | 로그인 시 발급받은 Refresh Token |
    | `deviceId` | `String` | 현재 기기의 고유 식별자 |
*   **Responses**
    *   **`200 OK`**: 토큰 재발급 성공.
        *   **Body**: `TokenBox` (회원가입 응답과 동일)
    *   `401 Unauthorized`: Refresh Token이 유효하지 않거나 만료됨

---

### 5. 로그아웃

*   **`POST /logout`**
*   **설명**: 사용자를 로그아웃 처리합니다. 서버에서 Refresh Token을 만료시키고, 현재 기기를 비활성화합니다.
*   **Request Headers**
    | Name | Value | 필수 여부 | 설명 |
    | :--- | :--- | :--- | :--- |
    | `Authorization` | `Bearer {AccessToken}` | **Yes** | 현재 로그인된 사용자의 Access Token |
*   **Request Body** (`Content-Type: application/json`)
    | 필드명 | 타입 | 설명 |
    | :--- | :--- | :--- |
    | `deviceId` | `String` | 로그아웃할 기기의 고유 식별자 |
*   **Responses**
    *   `200 OK`: 로그아웃 성공
    *   `401 Unauthorized`: Access Token이 유효하지 않음
