package com.company.organization.repository.income;

import com.company.organization.domain.branch.Warehouse;
import com.company.organization.domain.income.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface IncomeRepository extends JpaRepository<Income, Long> {

    @Query("select i from Income i where i.isActive = true and i.warehouse = ?1")
    ArrayList<Income> findAllByWarehouse(Warehouse warehouse);

}