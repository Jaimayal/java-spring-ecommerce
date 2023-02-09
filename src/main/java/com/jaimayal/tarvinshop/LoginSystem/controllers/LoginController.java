package com.jaimayal.tarvinshop.LoginSystem.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    
    @GetMapping("/")
    public String helloWorld() {
        return "Hello World";
    }
}
