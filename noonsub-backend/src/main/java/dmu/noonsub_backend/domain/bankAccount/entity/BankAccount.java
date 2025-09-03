package dmu.noonsub_backend.domain.bankAccount.entity;

import dmu.noonsub_backend.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private String fintechUseNum;        // 핀테크 이용 번호
    private String accountAlias;         // 계좌 별명
    private String bankCodeStd;          // 은행 표준코드
    private String bankCodeSub;          // 은행 지점코드
    private String bankName;             // 은행명
    private String savingsBankName;      // 저축은행명
    private String accountNumMasked;     // 마스킹된 계좌번호
    private String accountSeq;           // 회차번호
    private String accountHolderName;    // 예금주명
    private String accountHolderType;    // 계좌 구분 (P 등)
    private String accountType;          // 계좌 종류 (1, 2, 6, T 등)
    private String inquiryAgreeYn;       // 조회 동의 여부
    private String inquiryAgreeDtime;    // 조회 동의일시
    private String transferAgreeYn;      // 출금 동의 여부
    private String transferAgreeDtime;   // 출금 동의일시
    private String payerNum;             // 납부자 번호
}

