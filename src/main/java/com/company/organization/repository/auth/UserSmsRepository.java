package com.company.organization.repository.auth;

import com.company.organization.domain.user.UserSms;
import com.company.organization.enums.SmsCodeType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserSmsRepository extends JpaRepository<UserSms, Integer> {

    @Query("select u from UserSms u where u.expired = false and u.type = ?2 and u.userId = ?1 and u.toTime > CURRENT_TIMESTAMP")
    UserSms findByUserId(Long userId, SmsCodeType type);


    @Transactional
    @Modifying
    @Query("update UserSms u set u.expired = true where u.toTime < CURRENT_TIMESTAMP")
    void updateExpired();

}