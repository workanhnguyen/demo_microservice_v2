package com.nva.authservice.exceptions;

public class EntityExistedException extends RuntimeException {
    public EntityExistedException(String message) {
        super(message);
    }
}
