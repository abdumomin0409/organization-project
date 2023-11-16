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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_product_id", referencedColumnName = "id")
    private OrganizationProduct organizationProduct;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "outcome_id", referencedColumnName = "id")
    private Outcome outcome;

    private Double productPrise;
}
