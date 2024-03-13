package com.nva.authservice.exceptions;

public class CustomAuthenticationException extends RuntimeException {
    public CustomAuthenticationException(String message) {
        super(message);
    }
}
