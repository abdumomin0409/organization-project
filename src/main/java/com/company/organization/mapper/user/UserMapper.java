package com.company.organization.mapper.user;

import com.company.organization.domain.user.User;
import com.company.organization.payload.user.UserSignUpDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    User toEntity(UserSignUpDto dto);

}
