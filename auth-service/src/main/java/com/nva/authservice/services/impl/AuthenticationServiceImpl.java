package com.nva.authservice.services.impl;

import com.nva.authservice.dtos.JwtAuthenticationResponse;
import com.nva.authservice.dtos.SignInRequest;
import com.nva.authservice.dtos.SignUpRequest;
import com.nva.authservice.entities.User;
import com.nva.authservice.exceptions.UserExistedException;
import com.nva.authservice.exceptions.UserNotFoundException;
import com.nva.authservice.repositories.UserRepository;
import com.nva.authservice.services.AuthenticationService;
import com.nva.authservice.services.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    public void signup(SignUpRequest signUpRequest) {
        if (userRepository.findByEmail(signUpRequest.getEmail()).isEmpty()) {
            User user = new User();
            user.setEmail(signUpRequest.getEmail());
            user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

            userRepository.save(user);
        } else {
            throw new UserExistedException("Email đã được sử dụng.");
        }
    }

    @Override
    public JwtAuthenticationResponse signin(SignInRequest signInRequest) {
        try {
            Optional<User> user = userRepository.findByEmail(signInRequest.getEmail());
            if (user.isPresent()) {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));

            }
        } catch (AuthenticationException e) {
            throw new UserNotFoundException("Email hoặc mật khẩu không chính xác");
        }

        var user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password!"));
        var token = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(token);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }
}
