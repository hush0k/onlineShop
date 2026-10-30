package com.onlineShop.product.dto.response;

import com.onlineShop.product.WeightType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record ProductResponseDto(
        UUID id,
        String name ,
        String description,
        BigDecimal price,
        BigDecimal weight,
        WeightType weightType,
        Instant createdAt,
        Instant updatedAt
) {
}
