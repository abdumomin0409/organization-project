package com.company.organization.repository.organization;

import com.company.organization.domain.organization.OrganizationProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationProductRepository extends JpaRepository<OrganizationProduct, Long> {
}