package com.company.organization.repository.income;

import com.company.organization.domain.income.IncomeProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeProductRepository extends JpaRepository<IncomeProduct, Long> {
}