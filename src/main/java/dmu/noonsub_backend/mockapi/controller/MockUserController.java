package dmu.noonsub_backend.mockapi.controller;

import dmu.noonsub_backend.mockapi.dto.MockUserInfoResponseDto;
import dmu.noonsub_backend.mockapi.service.MockUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2.0/user")
public class MockUserController {

    private final MockUserService mockUserService;

    public MockUserController(MockUserService mockUserService) {
        this.mockUserService = mockUserService;
    }

    @GetMapping("/me")
    public ResponseEntity<MockUserInfoResponseDto> getUserInfo(
            @RequestHeader("Authorization") String authorization,
            @RequestParam("user_seq_no") String userSeqNo) {

        MockUserInfoResponseDto response = mockUserService.getUserInfo(userSeqNo);
        return ResponseEntity.ok(response);
    }
}
