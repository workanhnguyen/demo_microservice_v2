package com.nva.authservice.exceptions;

public class UserExistedException extends RuntimeException {
    public UserExistedException(String message) {
        super(message);
    }
}
