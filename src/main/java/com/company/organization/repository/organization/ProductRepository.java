package com.company.organization.repository.organization;

import com.company.organization.domain.organization.Product;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select (count(o) > 0) from Product o where o.isActive = true and upper(o.name) = upper(?1)")
    boolean existsByName(String name);

    @Query("select (count(o) > 0) from Product o where o.isActive = true and o.id = ?1")
    boolean existsById(@NotNull Long id);

    @Query("select o from Product o where o.isActive = true")
    Page<Product> findAllByPageable(Pageable pageable);

    @org.jetbrains.annotations.NotNull
    @Query("select p from Product p where p.isActive = true and p.id = ?1")
    @Override
    Optional<Product> findById(@org.jetbrains.annotations.NotNull Long id);
}