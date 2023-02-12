package com.jaimayal.tarvinshop.AuthSystem.service;

import com.jaimayal.tarvinshop.AuthSystem.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class JwtIssuerService {
    
    @Value("${jwt.token.duration}")
    private Long tokenDuration;
    @Value("${jwt.token.type}")
    private String tokenType;
    private final JwtEncoder encoder;
    private final UserService userService;
    
    @Autowired
    public JwtIssuerService(final JwtEncoder encoder, final UserService userService) {
        this.encoder = encoder;
        this.userService = userService;
    }

    public String getToken(final String userEmail) {
        Collection<Role> roles = this.userService.getUserRolesByEmail(userEmail);
        String scope = this.createScope(roles);
        JwtClaimsSet claims = this.createClaims(userEmail, scope);
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    private String createScope(final Collection<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.joining(" "));
    }

    private JwtClaimsSet createClaims(final String subject, final String scope) {
        Instant now = Instant.now();

        return JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(this.tokenDuration))
                .subject(subject)
                .claim("scope", scope)
                .build();
    }
    
    public Long getTokenDuration() {
        return this.tokenDuration;
    }

    public String getTokenType() {
        return this.tokenType;
    }
    
    public boolean isValid(String token) {
        return true;
    }
}
