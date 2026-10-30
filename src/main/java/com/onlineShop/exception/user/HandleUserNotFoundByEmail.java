package com.onlineShop.exception.user;

public class HandleUserNotFoundByEmail extends RuntimeException {
    public HandleUserNotFoundByEmail(String email) {
        super("User with " + email + " email is not found");
    }
}
