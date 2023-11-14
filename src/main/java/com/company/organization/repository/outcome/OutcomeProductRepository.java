package com.company.organization.repository.outcome;

import com.company.organization.domain.outcome.OutcomeProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutcomeProductRepository extends JpaRepository<OutcomeProduct, Long> {
}