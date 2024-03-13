package com.nva.authservice.dtos;

import com.nva.authservice.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String email;
    private String name;
    private Role role;
}
