package dmu.noonsub_backend.mockapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dmu.noonsub_backend.domain.openbanking.dto.OpenBankingUserInfoResponseDto;
import dmu.noonsub_backend.mockapi.service.MockUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v2.0/user")
public class MockUserController {

    private final MockUserService mockUserService;
    private final ObjectMapper objectMapper;

    public MockUserController(MockUserService mockUserService, ObjectMapper objectMapper) {
        this.mockUserService = mockUserService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/me")
    public ResponseEntity<OpenBankingUserInfoResponseDto> getUserInfo(
            @RequestHeader("Authorization") String authorization,
            @RequestParam("user_seq_no") String userSeqNo) {

        log.info(">> [RECV] MockUserController.getUserInfo - UserSeqNo: {}", userSeqNo);

        OpenBankingUserInfoResponseDto response = mockUserService.getUserInfo(userSeqNo);
        try {
            String responseBody = objectMapper.writeValueAsString(response);
            log.info("<< [SEND] MockUserController Response Body: {}", responseBody);
        } catch (JsonProcessingException e) {
            log.error("Error serializing response in MockUserController", e);
        }
        return ResponseEntity.ok(response);
    }
}
