package dmu.noonsub_backend.domain.member.entity;

import dmu.noonsub_backend.domain.bankAccount.entity.BankAccount;
import dmu.noonsub_backend.domain.common.BaseEntity;
import dmu.noonsub_backend.domain.member.enums.Role;
import dmu.noonsub_backend.domain.openbanking.entity.MemberBank;
import dmu.noonsub_backend.domain.openbanking.entity.OpenBankingToken;
import dmu.noonsub_backend.domain.subcribtion.entity.Subscription;
import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String residentNumber;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(unique = true)
    private String ci;

    @Column(nullable = false)
    private String mobileCarrier;

    @Column(nullable = false)
    private String pin;

    private boolean isBiometricEnabled = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subscription> subscriptions = new ArrayList<>();

    @JsonIgnore
    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private OpenBankingToken openBankingToken;

    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BankAccount> bankAccounts = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberBank> memberBanks = new ArrayList<>();

    private LocalDateTime memberDeletedAt;
}

