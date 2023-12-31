package com.company.organization.repository.auth;

import com.company.organization.domain.user.Roles;
import com.company.organization.domain.user.User;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from users u where u.phoneNumber = ?1")
    User findByPhoneNumber(String phoneNumber);

    @Query("select (count(u) > 0) from users u where u.phoneNumber = ?1")
    boolean existsByPhoneNumber(String phoneNumber);

    @Transactional
    @Modifying
    @Query("update users u set u.isActive = true where u.id = ?1")
    void updateStatusById(Long id);

    @Query("select u from users u where u.phoneNumber = ?1")
    Optional<User> optionalFindByPhoneNumber(String phoneNumber);

    @Transactional
    @Modifying
    @Query("update users u set u.roles = ?1 where u.phoneNumber = ?2")
    void promoteToSuperAdmin(Roles adminRole, String phoneNumber);

    @Override
    @Query("select u from users u where u.isActive = true and u.id = ?1")
    @NotNull
    Optional<User> findById(@NotNull Long id);

    @Override
    @Query("select (count(o) > 0) from users o where o.isActive = true and o.id = ?1")
    boolean existsById(@NotNull Long id);

}