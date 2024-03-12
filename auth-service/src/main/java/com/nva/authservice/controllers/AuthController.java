package com.nva.authservice.controllers;

import com.nva.authservice.entities.User;
import com.nva.authservice.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String addNewUser(@RequestBody User user) {
        return authService.saveUser(user);
    }

    @GetMapping("/token")
    public String getToken(User user) {
        return authService.generateToken(user.getName());
    }

    @PostMapping("/validateToken")
    public String validateToken(@RequestParam("token") String token) {
        authService.validateToken(token);
        return "Token is valid!";
    }
}
