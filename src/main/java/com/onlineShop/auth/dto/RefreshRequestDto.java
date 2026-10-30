package com.onlineShop.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record RefreshRequestDto(
        @NotBlank String refreshToken
) {}
