package com.jaimayal.tarvinshop.AuthSystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JwtBuilder {

    private final JwtEncoder encoder;
    @Value("${jwt.token.duration}")
    private Long jwtTokenDuration;
    

    @Autowired
    public JwtBuilder(final JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public Optional<String> getToken(final Authentication authentication) {
        try {
            String scope = this.getScope(authentication);
            JwtClaimsSet claims = this.getClaims(authentication, scope);
            String token = this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
            return Optional.of(token);
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    private String getScope(final Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
    }
    
    private JwtClaimsSet getClaims(final Authentication authentication, final String scope) {
        Instant now = Instant.now();
        
        return JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(this.jwtTokenDuration))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
    }
}
