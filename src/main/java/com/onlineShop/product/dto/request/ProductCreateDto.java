package com.onlineShop.product.dto.request;

import com.onlineShop.product.WeightType;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductCreateDto(

        @NotBlank(message = "Name can not be empty!")
        @Size(min=3, max=100, message = "Size of name should be more than 3 symbols and less than 100 symbols")
        String name,

        @Size(max=1000, message = "Size of description should be less than 1000 symbols")
        String description,

        UUID categoryId,

        @Positive(message = "Price should be more than 0")
        @NotNull(message = "Price can not be empty!")
        Integer stockQuantity,

        @NotNull(message = "Price can not be empty!")
        @Positive(message = "Price should be more than 0")
        @Digits(integer=10, fraction=2, message = "Price should have up to 10 integer digits and 2 fraction digits")
        BigDecimal price,

        @Positive(message = "Weight should be more than 0")
        @Digits(integer=10, fraction=3, message = "Weight should have up to 10 integer digits and 3 fraction digits")
        BigDecimal weight,

        WeightType weightType
) {
}
