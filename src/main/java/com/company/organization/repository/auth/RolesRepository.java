package com.company.organization.repository.auth;

import com.company.organization.domain.user.Roles;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Long> {

    @Query("select r from Roles r where r.isActive = true and r.name = ?1")
    Roles findByActiveAndName(String name);


    @Override
    @Query("select r from Roles r where r.isActive = true and r.id = ?1")
    @NotNull
    Optional<Roles> findById(@NotNull Long id);

    @Override
    @Query("select (count(o) > 0) from Roles o where o.isActive = true and o.id = ?1")
    boolean existsById(@NotNull Long id);

}