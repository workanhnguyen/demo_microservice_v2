package com.nva.authservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDto {
//    @Size(min = 4, message = "Password must be at least 4 characters long")
    private String password;
//    @Size(min = 4, message = "Password must be at least 4 characters long")
    private String newPassword;
//    @Size(min = 4, message = "Password must be at least 4 characters long")
    private String confirmNewPassword;
}
