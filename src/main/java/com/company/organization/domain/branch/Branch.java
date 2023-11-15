package com.company.organization.domain.branch;

import com.company.organization.domain.BaseDomain;
import com.company.organization.domain.organization.Organization;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Branch implements BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    private Organization organization;

    private LocalDateTime workBegin;

    private Boolean isActive = true;
}
