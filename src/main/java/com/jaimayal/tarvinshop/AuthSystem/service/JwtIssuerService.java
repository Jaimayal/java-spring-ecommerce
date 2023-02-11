package com.jaimayal.tarvinshop.AuthSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JwtIssuerService {

    private final JwtEncoder encoder;
    @Value("${jwt.token.duration}")
    private Long tokenDuration;
    
    @Value("${jwt.token.type}")
    private String tokenType;
    
    @Autowired
    public JwtIssuerService(final JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public String getToken(final String userEmail) {
        JwtClaimsSet claims = this.createClaims(userEmail);
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    private String createScope(final UserDetails userDetails) {
        return userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
    }

    private JwtClaimsSet createClaims(final String subject) {
        Instant now = Instant.now();

        return JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(this.tokenDuration))
                .subject(subject)
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
