package com.jaimayal.tarvinshop.AuthSystem.controller;

import com.jaimayal.tarvinshop.AuthSystem.dto.TokenResponseDTO;
import com.jaimayal.tarvinshop.AuthSystem.dto.UserLoginDTO;
import com.jaimayal.tarvinshop.AuthSystem.dto.UserRegisterDTO;
import com.jaimayal.tarvinshop.AuthSystem.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(final AuthService authService) {
        this.authService = authService;
    }
    
    @GetMapping("/")
    public String helloWorld(final Authentication authentication) {
        return "Hello " + authentication.getName() + " Your roles are: " + authentication.getAuthorities();
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody final UserLoginDTO userLoginDTO) {
        TokenResponseDTO tokenResponse = this.authService.login(userLoginDTO);
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody final UserRegisterDTO userRegisterDTO) {
        TokenResponseDTO tokenResponse = this.authService.register(userRegisterDTO);
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }
}
