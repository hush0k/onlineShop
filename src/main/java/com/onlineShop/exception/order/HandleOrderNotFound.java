package com.onlineShop.exception.order;

public class HandleOrderNotFound extends RuntimeException {
    public HandleOrderNotFound(Long orderId) {
        super("Order with " + orderId + " id is not found");
    }
}
