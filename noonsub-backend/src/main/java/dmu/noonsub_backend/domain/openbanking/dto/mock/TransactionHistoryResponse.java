package dmu.noonsub_backend.domain.openbanking.dto.mock;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TransactionHistoryResponse {
    private String apiTranId;
    private String rspCode;
    private String rspMessage;
    private String bankName;
    private String accountNumMasked;
    private String accountAlias;
    private List<TransactionItem> resList;

    @Data
    @AllArgsConstructor
    public static class TransactionItem {
        private String tranDate;
        private String tranTime;
        private String tranAmt;
        private String printContent;
        private String afterBalanceAmt;
        private String tranType;
    }
}
