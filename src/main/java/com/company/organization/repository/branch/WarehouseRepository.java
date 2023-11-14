package com.company.organization.repository.branch;

import com.company.organization.domain.branch.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}