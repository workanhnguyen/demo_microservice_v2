package com.nva.productservice.services;

public interface JwtService {
    String extractUsername(String token);
}
