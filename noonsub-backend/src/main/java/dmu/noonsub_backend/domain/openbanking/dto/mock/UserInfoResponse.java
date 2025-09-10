package dmu.noonsub_backend.domain.openbanking.dto.mock;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import dmu.noonsub_backend.domain.openbanking.dto.OpenBankingUserInfoResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserInfoResponse {
    private String apiTranId;
    private String rspCode;
    private String rspMessage;
    private String userSeqNo;
    private UserInfo userInfo;
    private List<AccountInfo> resList;

    @Data
    @AllArgsConstructor
    public static class UserInfo {
        private String userName;
        private String userBirth;
        private String userGender;
        private String userNationality;
        private String userCi;
    }

    @Data
    @AllArgsConstructor
    public static class AccountInfo {
        private String fintechUseNum;
        private String accountAlias;
        private String bankCodeStd;
        private String accountNumMasked;
        private String accountType;
        private String currencyCode;
        private String accountRegDt;
    }
}
