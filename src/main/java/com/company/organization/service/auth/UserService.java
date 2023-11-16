package com.company.organization.service.auth;

import com.company.organization.domain.user.User;
import com.company.organization.exception_handler.exception.ValidateException;
import com.company.organization.repository.auth.UserRepository;
import com.company.organization.service.BaseService;
import com.company.organization.service.organization.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class UserService implements BaseService {
    private final UserRepository userRepository;
    Logger logger = Logger.getLogger(UserService.class.getName());

    public User getById(Long id) {
        logger.log(Level.INFO, "UserService getById method called");
        return userRepository.findById(id).orElseThrow(() -> new ValidateException("User not found", -1400005));
    }

    public List<User> getAll() {
        logger.log(Level.INFO, "UserService getAll method called");
        return userRepository.findAll();
    }

    public boolean existsByName(String phoneNumber) {
        logger.log(Level.INFO, "UserService existsByPhoneNumber method called");
        return userRepository.existsByPhoneNumber(phoneNumber);
    }

    public boolean existsById(Long id) {
        logger.log(Level.INFO, "UserService existsById method called");
        return userRepository.existsById(id);
    }


}
