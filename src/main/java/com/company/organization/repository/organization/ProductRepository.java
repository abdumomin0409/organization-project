package com.company.organization.repository.organization;

import com.company.organization.domain.organization.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}