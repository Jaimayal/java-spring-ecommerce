package com.jaimayal.tarvinshop.AuthSystem.service;

import com.jaimayal.tarvinshop.AuthSystem.dto.TokenRefreshDTO;
import com.jaimayal.tarvinshop.AuthSystem.dto.TokenResponseDTO;
import com.jaimayal.tarvinshop.AuthSystem.dto.UserLoginDTO;
import com.jaimayal.tarvinshop.AuthSystem.dto.UserRegisterDTO;
import com.jaimayal.tarvinshop.AuthSystem.exception.TokenGenerationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    
    private final RoleService roleService;
    private final JwtService jwtService;
    private final UserService userService;

    @Autowired
    public AuthService(final RoleService roleService,
                       final JwtService jwtService,
                       final UserService userService) {
        this.roleService = roleService;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    public TokenResponseDTO register(UserRegisterDTO userRegister) {
        try {
            this.roleService.createRole("USER");
        } catch (Exception ignored) {}
        
        this.userService.createUser(userRegister);
        String email = userRegister.getEmail();
        
        return this.getJwtTokenResponse(email);
    }
    
    public TokenResponseDTO login(UserLoginDTO userLogin) {
        String email = userLogin.getEmail();
        String password = userLogin.getPassword();
        this.userService.checkCredentials(email, password);
        
        return this.getJwtTokenResponse(email);
    }

    public TokenResponseDTO refresh(TokenRefreshDTO tokenRefreshDTO) {
        String token = tokenRefreshDTO.getToken();
        boolean isValid = this.jwtService.isValid(token);
        if (!isValid) {
            throw new TokenGenerationException("The provided token is not valid");
        }

        String email = this.jwtService.getEmail(token);
        return this.getJwtTokenResponse(email);
    }
    
    private TokenResponseDTO getJwtTokenResponse(String email) {
        String token = this.jwtService.getToken(email);
        String tokenType = this.jwtService.getTokenType();
        Long expiresIn = this.jwtService.getTokenDuration();

        return new TokenResponseDTO(token, tokenType, expiresIn);
    }
}
