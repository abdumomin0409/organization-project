package com.company.organization.domain.user;

import com.company.organization.domain.BaseDomain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@ParameterObject
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements BaseDomain, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String phoneNumber;

    @JsonIgnore
    private String password;

    @ManyToOne
    private Roles roles = Roles.builder().createdAt(LocalDateTime.now()).name("USER").build();

    @Builder.Default
    private Boolean isActive = true;

    private LocalDateTime createdAt;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (getRoles() == null)
            return Collections.emptyList();
        return List.of(new SimpleGrantedAuthority("ROLE_" + getRoles().getName()));
    }

    @Override
    public String getUsername() {
        return getPhoneNumber();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !getIsActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return getRoles().getIsActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return getIsActive();
    }
}
