package com.company.organization.domain.user;

import com.company.organization.domain.BaseDomain;
import com.company.organization.enums.SmsCodeType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class UserSms implements BaseDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int randomCode;

    private Long userId;

    @Enumerated(EnumType.STRING)
     private SmsCodeType type = SmsCodeType.ACTIVATION;

    @CreationTimestamp
    @Column(columnDefinition = "timestamp default now()", updatable = false)
    private LocalDateTime fromTime;

    @Column(columnDefinition = "timestamp default now()+INTERVAL '3 Minutes'")
    private LocalDateTime toTime;

    private Boolean expired;
}
