package com.onlineShop.auth;

import com.onlineShop.auth.dto.RegisterRequestDto;
import com.onlineShop.user.dto.request.UserCreateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    @Mapping(target = "hashedPassword", source = "hashedPassword")
    UserCreateDto toUserCreateDto(RegisterRequestDto dto, String hashedPassword);
}
