package com.jaimayal.tarvinshop.categories.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryErrorResponseDTO {
    private final String message;
    private LocalDateTime time = LocalDateTime.now();
    private final int statusCode;
    private final String statusError;
}
