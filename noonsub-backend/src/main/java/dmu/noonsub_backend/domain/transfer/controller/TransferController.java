package dmu.noonsub_backend.domain.transfer.controller;

import dmu.noonsub_backend.global.auth.annotation.Requires2FA;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/transfer")
public class TransferController {

    @PostMapping
    @Requires2FA
    public ResponseEntity<Map<String, String>> transfer() {
        return ResponseEntity.ok().body(new HashMap<String, String>());
    }
}
