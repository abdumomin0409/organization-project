package com.company.organization.domain.outcome;

import com.company.organization.domain.BaseDomain;
import com.company.organization.domain.organization.OrganizationProduct;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OutcomeProduct implements BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private OrganizationProduct organizationProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    private Outcome outcome;

    private Double productPrise;
}
