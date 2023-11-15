package com.company.organization.service.auth;

import com.company.organization.domain.user.Roles;
import com.company.organization.domain.user.User;
import com.company.organization.domain.user.UserSms;
import com.company.organization.enums.SmsCodeType;
import com.company.organization.enums.TokenType;
import com.company.organization.event_listener.events.UserSmsSaveEvent;
import com.company.organization.exception_handler.exception.ValidateException;
import com.company.organization.mapper.user.UserMapper;
import com.company.organization.payload.auth.RefreshTokenRequest;
import com.company.organization.payload.auth.TokenResponse;
import com.company.organization.payload.user.UserResetPasswordDTO;
import com.company.organization.payload.user.UserSignInDto;
import com.company.organization.payload.user.UserSignUpDto;
import com.company.organization.payload.user.UserSmsDto;
import com.company.organization.repository.auth.RolesRepository;
import com.company.organization.repository.auth.UserRepository;
import com.company.organization.security.JwtUtils;
import com.company.organization.service.BaseService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService implements BaseService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserSmsService userSmsService;
    private final UserMapper userMapper;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final RolesRepository rolesRepository;

    public AuthService(@Lazy UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils,
                       UserSmsService userSmsService, UserMapper userMapper, ApplicationEventPublisher applicationEventPublisher, RolesRepository rolesRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userSmsService = userSmsService;
        this.userMapper = userMapper;
        this.applicationEventPublisher = applicationEventPublisher;
        this.rolesRepository = rolesRepository;
    }

    public User signUp(UserSignUpDto dto) {
        if (!dto.getPassword().equals(dto.getPrePassword()))
            throw new ValidateException("Passwords are not equal", -121219);
        if (!dto.getPhoneNumber().matches("^[+][0-9]{12}$"))
            throw new ValidateException("Invalid phone number", -121218);
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        User user = userMapper.USER_MAPPER.toEntity(dto);
        User saved = saveToDb(user);
        applicationEventPublisher.publishEvent(new UserSmsSaveEvent(saved, SmsCodeType.ACTIVATION));
        return saved;
    }

    public String activate(UserSmsDto dto) {
        User user = findByPhone(dto.getPhoneNumber());
        UserSms userSms = userSmsService.findByUserId(user.getId(), SmsCodeType.ACTIVATION);
        if (Objects.nonNull(userSms) && userSms.getRandomCode() == Integer.parseInt(dto.getCode())) {
            userSms.setExpired(true);
            userSmsService.update(userSms);
            userRepository.updateStatusById(user.getId());
            return "User successfully activated";
        }
        throw new ValidateException("Invalid code", -121217);
    }

    public TokenResponse getAccessToken(UserSignInDto dto) {
        String phoneNumber = dto.getPhoneNumber();
        String password = dto.getPassword();
        User user = findByPhone(phoneNumber);
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new ValidateException("Phone number or password is incorrect", -121216);
        userRepository.updateStatusById(user.getId());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(phoneNumber, password);
        this.authenticationManager.authenticate(authentication);
        return jwtUtils.generateToken(phoneNumber);
    }

    public TokenResponse refreshToken(RefreshTokenRequest dto) {
        String refreshToken = dto.refreshToken();
        if (!jwtUtils.isTokenValid(refreshToken, TokenType.REFRESH))
            throw new ValidateException("Refresh token is invalid", -121215);
        String phoneNumber = jwtUtils.getUsername(refreshToken, TokenType.REFRESH);
        userRepository.optionalFindByPhoneNumber(phoneNumber);
        TokenResponse tokenResponse = TokenResponse.builder()
                .refreshToken(refreshToken)
                .refreshTokenExpiry(jwtUtils.getExpiry(refreshToken, TokenType.REFRESH))
                .build();
        return jwtUtils.generateAccessToken(phoneNumber, tokenResponse);
    }


    public void resendCode(String phoneNumber, SmsCodeType smsCodeType) {
        User user = findByPhone(phoneNumber);
        applicationEventPublisher.publishEvent(new UserSmsSaveEvent(user, smsCodeType));
    }

    public void updateSuperAdmin(String superAdmin1) {
        User user = userRepository.findByPhoneNumber(superAdmin1);
        if (Objects.nonNull(user))
            userRepository.promoteToSuperAdmin(rolesRepository.findByActiveAndName("ROLE_ADMIN"), superAdmin1);
    }


    public void forgetPasswordActivate(UserResetPasswordDTO dto) {
        User user = findByPhone(dto.phoneNumber());
        UserSms userSms = userSmsService.findByUserId(user.getId(), SmsCodeType.FORGET_PASSWORD);
        if (Objects.nonNull(userSms) && userSms.getRandomCode() == Integer.parseInt(dto.code())) {
            userSms.setExpired(true);
            userSmsService.update(userSms);
            user.setPassword(passwordEncoder.encode(dto.password()));
            userRepository.save(user);
            return;
        }
        throw new ValidateException("Invalid code", -121213);
    }

    private User findByPhone(String phoneNumber) {
        return userRepository.optionalFindByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ValidateException("Phone number not found", -121214));
    }

    public boolean existsByPhoneNumber(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }

    public User saveToDb(User user) {
        return userRepository.save(user);
    }

}
