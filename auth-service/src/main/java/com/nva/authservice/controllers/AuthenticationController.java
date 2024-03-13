package com.nva.authservice.controllers;

import com.nva.authservice.dtos.*;
import com.nva.authservice.services.AuthenticationService;
import com.nva.authservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest) {
        authenticationService.signup(signUpRequest);
        return ResponseEntity.ok(Collections.singletonMap("message", "Đăng ký tài khoản thành công!"));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.signin(signInRequest));
    }

    @PostMapping("/validateToken")
    public ResponseEntity<?> validateToken(@RequestParam("token") String token) {
        authenticationService.validateToken(token);
        return ResponseEntity.ok(Collections.singletonMap("message", "Token is valid!"));
    }

    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getProfile() {
        return ResponseEntity.ok(userService.getProfile());
    }
}
