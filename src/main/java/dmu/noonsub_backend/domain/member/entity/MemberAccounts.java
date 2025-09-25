package dmu.noonsub_backend.domain.member.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor
public class MemberAccounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column
    private String fintechUseNum;

    @Column(nullable = false)
    private String accountAlias; // 계좌 별칭

    @Column(nullable = false)
    private String bankCodeStd; // 은행 코드

    @Column
    private String bankCodeSub;

    @Column(nullable = false)
    private String bankName;

    @Column(nullable = false)
    private String accountNumMasked; // 마스킹된 계좌번호

    @Column(nullable = false)
    private String accountHolderName; // 예금주명

    @Column(nullable = false)
    private String accountHolderType; // 계좌 구분 (P 등)

    @Column
    private String accountType; // 계좌 종류 (1, 2, 6, T 등)

    @Column
    private String inquiryAgreeYn; // 조회 동의 여부

    @Column
    private Instant inquiryAgreeDtime; // 조회 동의일시

    @Column
    private String transferAgreeYn; // 출금 동의 여부

    @Column
    private Instant transferAgreeDtime; // 출금 동의일시

    @Builder
    public MemberAccounts(
            Member member, String fintechUseNum, String accountAlias,
            String bankCodeStd, String bankCodeSub, String bankName,
            String accountNumMasked, String accountHolderName, String accountHolderType,
            String accountType, String inquiryAgreeYn, Instant inquiryAgreeDtime,
            String transferAgreeYn, Instant transferAgreeDtime) {
        this.member = member;
        this.fintechUseNum = fintechUseNum;
        this.accountAlias = accountAlias;
        this.bankCodeStd = bankCodeStd;
        this.bankCodeSub = bankCodeSub;
        this.bankName = bankName;
        this.accountNumMasked = accountNumMasked;
        this.accountHolderName = accountHolderName;
        this.accountHolderType = accountHolderType;
        this.accountType = accountType;
        this.inquiryAgreeYn = inquiryAgreeYn;
        this.inquiryAgreeDtime = inquiryAgreeDtime;
        this.transferAgreeYn = transferAgreeYn;
        this.transferAgreeDtime = transferAgreeDtime;
    }
}
