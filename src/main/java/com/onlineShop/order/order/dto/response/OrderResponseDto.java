package com.onlineShop.order.order.dto.response;

import com.onlineShop.order.order.Currency;
import com.onlineShop.order.orderHistory.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;

public record OrderResponseDto(
        Long id,
        Long userId,
        OrderStatus status,
        BigDecimal subPrice,
        BigDecimal deliveryPrice,
        BigDecimal discount,
        BigDecimal totalPrice,
        Currency currency,
        String deliveryCity,
        String deliveryAddress,
        Instant predictedDateOfDelivery,
        Instant createdAt
) {
}
