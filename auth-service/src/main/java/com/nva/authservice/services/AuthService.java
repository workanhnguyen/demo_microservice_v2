package com.nva.authservice.services;

import com.nva.authservice.entities.User;

public interface AuthService {
    String saveUser(User user);
    String generateToken(String username);
    void validateToken(String token);
}
