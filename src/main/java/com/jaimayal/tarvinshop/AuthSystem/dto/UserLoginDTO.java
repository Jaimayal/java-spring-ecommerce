package com.jaimayal.tarvinshop.AuthSystem.dto;

import lombok.Data;

@Data
public class UserLoginDTO {
    private final String email;
    private final String password;
}
