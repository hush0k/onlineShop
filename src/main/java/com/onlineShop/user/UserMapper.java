package com.onlineShop.user;

import com.onlineShop.user.dto.request.UserCreateDto;
import com.onlineShop.user.dto.request.UserUpdateDto;
import com.onlineShop.user.dto.response.UserResponseDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserCreateDto createDto);
    UserResponseDto toUserResponse(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UserUpdateDto dto, @MappingTarget User user);

}
