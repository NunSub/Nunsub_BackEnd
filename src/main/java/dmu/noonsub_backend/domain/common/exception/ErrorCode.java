package dmu.noonsub_backend.domain.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    // Common
    UNAUTHORIZED(401, "C001", "인증되지 않은 사용자입니다."),
    FORBIDDEN(403, "C002", "권한이 없습니다."),
    INVALID_INPUT_VALUE(400, "C003", "유효하지 않은 입력 값입니다."),
    INVALID_TOKEN(400, "C004", "유효하지 않은 토큰입니다."),
    INTERNAL_SERVER_ERROR(500, "C005", "서버에 문제가 발생하였습니다."),
    NOT_FOUND(404, "C006", "해당 리소스를 찾을 수 없습니다."),
    DATA_SAVE_FAILED(500, "C007", "데이터 저장에 실패하였습니다."),
    NO_DATA_FOUND(404, "C008", "조회 결과가 없습니다."),
    EXTERNAL_API_ERROR(500, "C009", "외부 API 호출에 실패하였습니다."),
    USER_NOT_FOUND(400, "C010", "사용자를 찾을 수 없습니다."),
    TOKEN_PARSE_FAILED(400, "C011", "토큰 데이터를 파싱하거나 복호화하는 데 실패했습니다."),

    // Member
    MEMBER_NOT_FOUND(404, "M001", "해당 회원을 찾을 수 없습니다."),
    LOGIN_FAILED(400, "M002", "로그인에 실패하였습니다."),
    SIGNUP_FAILED(400, "M003", "회원가입에 실패하였습니다."),
    PASSWORD_MISMATCH(400, "M004", "비밀번호가 일치하지 않습니다."),
    DUPLICATE_MEMBER(409, "M005", "이미 가입된 회원입니다."),
    TOKEN_EXPIRED(401, "M006", "토큰이 만료되었습니다."),
    MISSING_2FA_TOKEN(400, "M007", "2차 인증 토큰이 없습니다."),
    INVALID_2FA_TOKEN(400, "M008", "유효하지 않은 2차 인증 토큰입니다."),
    USER_MISMATCH_2FA(400, "M009", "2차 인증 사용자 정보가 일치하지 않습니다."),
    PIN_NOT_SET(400, "M010", "PIN 번호가 설정되지 않았습니다."),
    // Firebase

    DEVICE_NOT_FOUND(402, "D001", "등록된 기기가 없습니다."),
    BANK_ACCOUNT_NOT_FOUND(400, "D002", "등록된 은행 계좌가 없습니다."),

    // Transaction
    TRANSACTION_NOT_FOUND(400, "T001", "해당 거래내역을 찾을 수 없습니다."),

    // Open Banking
    OPENBANKING_SYNC_FAILED(400, "O001", "오픈뱅킹 동기화에 실패하였습니다."),

    // Subscription
    SUBSCRIPTION_NOT_FOUND(400, "S001", "해당 구독 정보를 찾을 수 없습니다."),
    SUBSCRIPTION_ALREADY_EXISTS(400, "S002", "이미 존재하는 구독 서비스입니다."),;

    private final int status;
    private final String code;
    private final String message;
}

