package com.jaimayal.tarvinshop.AuthSystem.service;

import com.jaimayal.tarvinshop.AuthSystem.dto.TokenResponseDTO;
import com.jaimayal.tarvinshop.AuthSystem.dto.UserLoginDTO;
import com.jaimayal.tarvinshop.AuthSystem.dto.UserRegisterDTO;
import com.jaimayal.tarvinshop.AuthSystem.exception.PasswordDoesNotMatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final RoleService roleService;
    private final JwtIssuerService jwtIssuerService;
    private final UserService userService;

    @Autowired
    public AuthService(final RoleService roleService, 
                       final JwtIssuerService jwtIssuerService,
                       final UserService userService) {
        this.roleService = roleService;
        this.jwtIssuerService = jwtIssuerService;
        this.userService = userService;
    }

    public TokenResponseDTO register(UserRegisterDTO userRegister) {
        try {
            this.roleService.createRole("USER");
        } catch (Exception ignored) {}
        
        this.userService.createUser(userRegister);
        String email = userRegister.getEmail();
        String password = userRegister.getPassword();
        
        return this.getJwtToken(email, password);
    }
    
    public TokenResponseDTO login(UserLoginDTO userLogin) {
        String email = userLogin.getEmail();
        String password = userLogin.getPassword();
        
        return this.getJwtToken(email, password);
    }
    
    private TokenResponseDTO getJwtToken(String email, String password) {
        boolean isPasswordValid = this.userService.areCredentialsValid(email, password);
        if (!isPasswordValid) {
            throw new PasswordDoesNotMatchException("The user password is not correct");
        }
        
        String token = this.jwtIssuerService.getToken(email);
        String tokenType = this.jwtIssuerService.getTokenType();
        Long expiresIn = this.jwtIssuerService.getTokenDuration();

        return new TokenResponseDTO(token, tokenType, expiresIn);
    }
}
