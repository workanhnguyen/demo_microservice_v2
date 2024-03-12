package com.nva.authservice.services;

import java.util.Map;

public interface JwtService {
    void validateToken(final String token);
    String generateToken(String username);
}
