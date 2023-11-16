package com.company.organization.repository.organization;

import com.company.organization.domain.organization.Organization;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    @Query("select (count(o) > 0) from Organization o where o.isActive = true and upper(o.name) = upper(?1)")
    boolean existsByName(String name);

    @Query("select (count(o) > 0) from Organization o where o.isActive = true and o.id = ?1")
    boolean existsById(@NotNull Long id);

    @Query("select o from Organization o where o.isActive = true")
    Page<Organization> findAllByPageable(Pageable pageable);

    @Override
    @Query("select o from Organization o where o.isActive = true and o.id = ?1")
    @NotNull
    Optional<Organization> findById(@NotNull Long id);


}