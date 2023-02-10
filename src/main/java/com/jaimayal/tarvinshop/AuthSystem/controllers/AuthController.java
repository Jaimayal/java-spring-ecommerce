package com.jaimayal.tarvinshop.AuthSystem.controllers;

import com.jaimayal.tarvinshop.AuthSystem.security.JwtBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AuthController {
    private final JwtBuilder jwtBuilder;

    @Autowired
    public AuthController(final JwtBuilder jwtBuilder) {
        this.jwtBuilder = jwtBuilder;
    }
    
    @GetMapping("/")
    public String helloWorld() {
        return "Hello World";
    }
    
    @PostMapping("/token")
    public String token(final Authentication authentication) {
        return this.jwtBuilder
                .getToken(authentication)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Token could not be generated."
                ));
    }
}
