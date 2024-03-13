package com.nva.authservice.services.impl;

import com.nva.authservice.dtos.UserResponse;
import com.nva.authservice.entities.User;
import com.nva.authservice.exceptions.UserExistedException;
import com.nva.authservice.exceptions.UserNotFoundException;
import com.nva.authservice.repositories.UserRepository;
import com.nva.authservice.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if (optionalUser.isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
        throw new UserExistedException("Email is already taken.");
    }

    @Override
    public UserResponse getProfile() {
        User existingUser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new UserNotFoundException("User is not found."));
        ;

        UserResponse response = new UserResponse();
        response.setEmail(existingUser.getEmail());
        response.setName(existingUser.getName());
        response.setRole(existingUser.getRole());

        return response;
    }
}
