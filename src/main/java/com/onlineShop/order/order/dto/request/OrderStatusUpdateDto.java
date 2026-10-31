package com.onlineShop.order.order.dto.request;

import com.onlineShop.order.orderHistory.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record OrderStatusUpdateDto(

        @NotNull(message = "Status can not be empty!")
        OrderStatus status
) {
}
