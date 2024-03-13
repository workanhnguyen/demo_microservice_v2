package com.nva.authservice.services;

import com.nva.authservice.dtos.ChangePasswordDto;
import com.nva.authservice.dtos.ChangePasswordRequest;
import com.nva.authservice.dtos.EditUserRequest;
import com.nva.authservice.dtos.UserResponse;
import com.nva.authservice.entities.User;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    UserResponse getProfile();
}
