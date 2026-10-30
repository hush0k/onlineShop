package com.onlineShop.exception.category;

public class HandleCategoryAlreadyExists extends RuntimeException {
    public HandleCategoryAlreadyExists(String name) {
        super("Category with name: " + name + " is already exists.");
    }
}
