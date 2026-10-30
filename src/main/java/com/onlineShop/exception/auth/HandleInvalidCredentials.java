package com.onlineShop.exception.auth;

public class HandleInvalidCredentials extends RuntimeException {
    public HandleInvalidCredentials() {
        super("Invalid email or password");
    }
}
