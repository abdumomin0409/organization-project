package com.company.organization.repository.outcome;

import com.company.organization.domain.outcome.Outcome;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutcomeRepository extends JpaRepository<Outcome, Long> {
}