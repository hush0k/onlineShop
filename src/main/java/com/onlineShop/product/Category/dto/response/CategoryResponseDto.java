package com.onlineShop.product.Category.dto.response;

import java.util.UUID;

public record CategoryResponseDto(
        UUID id,
        String name
) {
}
