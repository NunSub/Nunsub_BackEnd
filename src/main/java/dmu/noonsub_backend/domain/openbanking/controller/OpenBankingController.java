package dmu.noonsub_backend.domain.openbanking.controller;

import dmu.noonsub_backend.domain.openbanking.dto.*;
import dmu.noonsub_backend.domain.openbanking.service.OpenBankingService;
import dmu.noonsub_backend.global.userdetails.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "OpenBanking (오픈뱅킹 연동)", description = "오픈뱅킹 인증 및 계좌 연동/해지 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OpenBankingController {

    private final OpenBankingService openBankingService;

    @Operation(summary = "오픈뱅킹 인증 URL 요청", description = "사용자 인증 및 계좌 등록을 위한 오픈뱅킹인증 페이지 URL을 반환합니다.")
    @PostMapping("/bank/authorize")
    public ResponseEntity<AuthorizeResponseDto> authorize(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        String phoneNumber = userDetails.getUsername();
        String authorizeUrl = openBankingService.getAuthorizationUrl(phoneNumber);
        return ResponseEntity.ok(new AuthorizeResponseDto(authorizeUrl));
    }

    @Operation(summary = "오픈뱅킹 토큰 교환 (수동)", description = "클라이언트가 수동으로 인증 코드를 전달하여 토큰을 교환할 때 사용합니다. (현재는 /openbanking/callback에서 자동으로 처리되므로 거의 사용되지 않습니다.)")
    @PostMapping("/bank/exchange")
    public ResponseEntity<Void> exchangeToken(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody TokenExchangeRequestDto requestDto) {

        log.info(">> [START] OpenBankingController.exchangeToken - User: {}", userDetails.getUsername());

        String phoneNumber = userDetails.getUsername();
        openBankingService.exchangeTokenAndLinkMember(phoneNumber, requestDto);
        openBankingService.syncOpenBankingInfo(phoneNumber);
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "오픈뱅킹 계좌 연결 해제", description = "사용자의 특정 계좌(fintechUseNum)를 서비스에서 연결 해제합니다.")
    @PostMapping("/bank/unlink")
    public ResponseEntity<Void> unlinkAccount(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody UnlinkRequestDto requestDto) {
        String phoneNumber = userDetails.getUsername();
        openBankingService.unlinkAccount(phoneNumber, requestDto.getFintechUseNum());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "오픈뱅킹 인증 콜백 처리",
            description = "오픈뱅킹 인증 완료 후, 리디렉션되는 엔드포인트입니다. 인증 코드를 받아 토큰 발급 및 계좌 정보 동기화를 자동으로 처리합니다.")
    @GetMapping("/openbanking/callback")
    public ResponseEntity<OpenBankingUserInfoResponseDto> openBankingCallback(
            @Parameter(hidden = true)
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Parameter(description = "오픈뱅킹에서 발급한 임시 인증 코드", required = true)
            @RequestParam("code") String code,
            @Parameter(description = "CSRF 방지를 위한 상태 값", required = true)
            @RequestParam("state") String state) {
        // state 값을 이용해 Redis에서 사용자 전화번호를 조회
        String phoneNumber = openBankingService.getPhoneNumberByState(state);
        if (phoneNumber == null) {
            // 유효하지 않은 state는 bad request로 처리
            log.error("!! Invalid or expired state parameter received in callback: {}", state);
            return ResponseEntity.badRequest().build();
        }

        // DTO를 생성하여 토큰 교환 요청
        TokenExchangeRequestDto requestDto = new TokenExchangeRequestDto();
        requestDto.setCode(code);
        requestDto.setState(state);
        log.info(">> Successfully processed OpenBanking callback for user: {}", phoneNumber);

        log.info(">> [START] OpenBankingController.exchangeToken - User: {}", userDetails.getUsername());
        openBankingService.exchangeTokenAndLinkMember(phoneNumber, requestDto);
        OpenBankingUserInfoResponseDto userInfo = openBankingService.syncOpenBankingInfo(phoneNumber);

        return ResponseEntity.ok(userInfo);
    }

}
