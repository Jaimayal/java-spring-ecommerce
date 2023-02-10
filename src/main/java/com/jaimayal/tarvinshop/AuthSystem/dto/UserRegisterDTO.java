package com.jaimayal.tarvinshop.AuthSystem.dto;

import lombok.Data;

@Data
public class UserRegisterDTO {
    private final String email;
    private final String password;
    private final String rol;
    private final String token;
}
