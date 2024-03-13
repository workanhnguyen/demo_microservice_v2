package com.nva.authservice.exceptions;

public class InvalidApiEndpointException extends RuntimeException {
    public InvalidApiEndpointException(String message) {
        super(message);
    }
}
