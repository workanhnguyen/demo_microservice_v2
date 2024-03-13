package com.nva.authservice.dtos;

import lombok.Data;

@Data
public class ExceptionResponse {
    private int status;
    private String message;
    private Long timestamp;
}
