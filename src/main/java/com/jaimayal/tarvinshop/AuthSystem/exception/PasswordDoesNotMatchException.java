package com.jaimayal.tarvinshop.AuthSystem.exception;

public class PasswordDoesNotMatchException extends RuntimeException {
    public PasswordDoesNotMatchException(String message) {
        super(message);
    }
}
