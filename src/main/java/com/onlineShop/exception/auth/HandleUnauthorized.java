package com.onlineShop.exception.auth;

public class HandleUnauthorized extends RuntimeException {
    public HandleUnauthorized() {
        super("User is not authenticated");
    }
}
