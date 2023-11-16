package com.company.organization.repository.income;

import com.company.organization.domain.income.IncomeProduct;
import com.company.organization.domain.organization.OrganizationProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IncomeProductRepository extends JpaRepository<IncomeProduct, Long> {

    @Query("select (count(i) > 0) from IncomeProduct i where i.organizationProduct.id = ?1")
    boolean existsByOrganizationProduct(Long id);

    @Query("select i from IncomeProduct i where i.organizationProduct.id = ?1")
    IncomeProduct findByOrganizationProduct(Long organizationProduct);

}