package com.company.organization.domain.organization;

import com.company.organization.domain.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class OrganizationProduct extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Organization organization;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    private Boolean isActive = true;

    @Builder(builderMethodName = "organizationProductBuilder")
    public OrganizationProduct(Long createdBy, Long updateBy, LocalDateTime createdAt, LocalDateTime updatedAt, Long id, Organization organization,
                               Product product, Boolean isActive) {
        super(createdBy, updateBy, createdAt, updatedAt);
        this.id = id;
        this.organization = organization;
        this.product = product;
        this.isActive = isActive;
    }
}
