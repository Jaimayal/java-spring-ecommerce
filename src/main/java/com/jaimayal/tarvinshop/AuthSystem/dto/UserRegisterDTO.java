package com.jaimayal.tarvinshop.AuthSystem.dto;

import lombok.Data;

@Data
public class UserRegisterDTO {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
}
