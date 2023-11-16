package com.company.organization.domain.income;

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
public class IncomeProduct implements BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private OrganizationProduct organizationProduct;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private Income income;

    private Double productPrise;
}
