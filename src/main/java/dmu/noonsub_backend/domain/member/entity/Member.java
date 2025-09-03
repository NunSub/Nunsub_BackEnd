package dmu.noonsub_backend.domain.member.entity;

import dmu.noonsub_backend.domain.common.BaseEntity;
import dmu.noonsub_backend.domain.member.enums.Role;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

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

    private LocalDateTime memberDeletedAt;
}

