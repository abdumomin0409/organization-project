package com.company.organization.repository.outcome;

import com.company.organization.domain.branch.Warehouse;
import com.company.organization.domain.outcome.Outcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface OutcomeRepository extends JpaRepository<Outcome, Long> {

    @Query("select i from Outcome i where i.isActive = true and i.warehouse = ?1")
    ArrayList<Outcome> findAllByWarehouse(Warehouse warehouse);

}