package com.nva.authservice.dtos;

import lombok.Data;

@Data
public class SignUpRequest {
    private String email;
    private String password;
}
