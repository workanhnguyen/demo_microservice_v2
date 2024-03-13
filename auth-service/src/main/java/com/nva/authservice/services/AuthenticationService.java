package com.nva.authservice.services;

import com.nva.authservice.dtos.JwtAuthenticationResponse;
import com.nva.authservice.dtos.RefreshTokenRequest;
import com.nva.authservice.dtos.SignInRequest;
import com.nva.authservice.dtos.SignUpRequest;

public interface AuthenticationService {
    void signup(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signin(SignInRequest signInRequest);
    void validateToken(String token);
}
