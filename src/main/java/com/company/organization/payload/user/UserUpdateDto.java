package com.company.organization.payload.user;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateDto {
    private String fullName;
    private String phoneNumber;
    private String password;
    private String prePassword;
}