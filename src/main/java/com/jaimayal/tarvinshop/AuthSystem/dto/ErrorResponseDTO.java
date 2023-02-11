package com.jaimayal.tarvinshop.AuthSystem.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponseDTO {
    private final String message;
    private LocalDateTime time = LocalDateTime.now();
    private final int statusCode;
    private final String statusError;
}
