package com.company.organization.repository.auth;

import com.company.organization.domain.user.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RolesRepository extends JpaRepository<Roles, Long> {

    @Query("select r from Roles r where r.isActive = true and r.name = ?1")
    Roles findByActiveAndName(String name);

}