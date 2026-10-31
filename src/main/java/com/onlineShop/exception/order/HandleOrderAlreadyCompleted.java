package com.onlineShop.exception.order;

import com.onlineShop.order.orderHistory.OrderStatus;

public class HandleOrderAlreadyCompleted extends RuntimeException {
    public HandleOrderAlreadyCompleted(OrderStatus status) {
        super("Next step is not possible: order has already been " + status.name().toLowerCase());
    }

    public HandleOrderAlreadyCompleted(OrderStatus current, OrderStatus next) {
        super("Status transition from " + current.name() + " to " + next.name() + " is not allowed");
    }
}
