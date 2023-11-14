package com.company.organization.repository.branch;

import com.company.organization.domain.branch.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {
}