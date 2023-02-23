package com.jaimayal.tarvinshop.Product.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductErrorResponseDTO {
    private final String message;
    private LocalDateTime time = LocalDateTime.now();
    private final int statusCode;
    private final String statusError;
}