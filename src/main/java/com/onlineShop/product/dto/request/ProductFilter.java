package com.onlineShop.product.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductFilter(
        UUID categoryId,
        BigDecimal minPrice,
        BigDecimal maxPrice
) {}
