package com.onlineShop.user.dto.request;

import com.onlineShop.user.UserRole;
import jakarta.validation.constraints.NotBlank;

public record UserRoleChangeDto(

        @NotBlank(message = "This field cannot be empty")
        UserRole userRole

) {}
