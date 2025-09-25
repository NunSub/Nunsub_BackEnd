package dmu.noonsub_backend.mockapi.controller;

import dmu.noonsub_backend.mockapi.dto.MockTransactionResponseDto;
import dmu.noonsub_backend.mockapi.service.MockTransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2.0")
public class MockTransferController {

    private final MockTransferService mockTransferService;

    public MockTransferController(MockTransferService mockTransferService) {
        this.mockTransferService = mockTransferService;
    }

    @GetMapping("/account/transaction_list/fin_num")
    public ResponseEntity<MockTransactionResponseDto> getTransactionHistory(
            @RequestParam String bank_tran_id,
            @RequestParam String fintech_use_num,
            @RequestParam String inquiry_type,
            @RequestParam String inquiry_base,
            @RequestParam String from_date,
            @RequestParam(required = false) String from_time,
            @RequestParam String to_date,
            @RequestParam(required = false) String to_time,
            @RequestParam String sort_order,
            @RequestParam String tran_dtime,
            @RequestParam(required = false) String before_inquiry_trace_info
    ) {

        MockTransactionResponseDto response = mockTransferService.getTransactionHistory(fintech_use_num,
                from_date, to_date, inquiry_type, before_inquiry_trace_info);
        return ResponseEntity.ok(response);
    }
}
