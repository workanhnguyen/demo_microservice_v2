package com.nva.authservice.services;

public interface JwtService {
    void validateToken(final String token);
    String generateToken(String username);
}
