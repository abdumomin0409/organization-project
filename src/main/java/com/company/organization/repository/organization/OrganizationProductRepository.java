package com.company.organization.repository.organization;

import com.company.organization.domain.organization.OrganizationProduct;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrganizationProductRepository extends JpaRepository<OrganizationProduct, Long> {

    @Query("select (count(o) > 0) from OrganizationProduct o where o.organization.id = ?1 and o.product.id = ?2")
    boolean existsByOrganizationAndProduct(Long organization, Long product);


    @Override
    @Query("select o from OrganizationProduct o where o.isActive = true and o.id = ?1")
    @NotNull
    Optional<OrganizationProduct> findById(@NotNull Long id);

    @Query("select o from OrganizationProduct o where o.isActive = true")
    Page<OrganizationProduct> findAllByPageable(Pageable pageable);

    @Override
    @Query("select (count(o) > 0) from OrganizationProduct o where o.isActive = true and o.id = ?1")
    boolean existsById(@NotNull Long id);

}