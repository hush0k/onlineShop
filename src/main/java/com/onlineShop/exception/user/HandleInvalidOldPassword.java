package com.onlineShop.exception.user;

public class HandleInvalidOldPassword extends RuntimeException {
    public HandleInvalidOldPassword() {
        super("Old password is incorrect");
    }
}
