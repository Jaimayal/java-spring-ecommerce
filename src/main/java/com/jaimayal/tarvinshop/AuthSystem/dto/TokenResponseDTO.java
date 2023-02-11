package com.jaimayal.tarvinshop.AuthSystem.dto;

import lombok.Data;

@Data
public class TokenResponseDTO {
    private final String token;
    private final String tokenType;
    private final Long expiresIn;
}
