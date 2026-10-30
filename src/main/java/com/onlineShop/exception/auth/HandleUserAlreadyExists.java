package com.onlineShop.exception.auth;

public class HandleUserAlreadyExists extends RuntimeException {
    public HandleUserAlreadyExists(String email) {
        super("User with email: " + email + " is already exists.");
    }
}
