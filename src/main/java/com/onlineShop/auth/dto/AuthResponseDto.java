package com.onlineShop.auth.dto;

public record AuthResponseDto(
        String accessToken,
        String refreshToken
) {}
