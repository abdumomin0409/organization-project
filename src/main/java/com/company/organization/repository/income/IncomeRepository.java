package com.company.organization.repository.income;

import com.company.organization.domain.income.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income, Long> {
}