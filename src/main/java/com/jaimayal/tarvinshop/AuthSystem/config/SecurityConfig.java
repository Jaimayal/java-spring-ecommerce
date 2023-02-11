package com.jaimayal.tarvinshop.AuthSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authConfig) -> { 
                    authConfig
                            .mvcMatchers("/login").permitAll()
                            .mvcMatchers("/register").permitAll()
                            .anyRequest().authenticated();
                })
                .csrf((csrfConfig) -> csrfConfig
                        .ignoringRequestMatchers(
                                new MvcRequestMatcher(new HandlerMappingIntrospector(), "/login"),
                                new MvcRequestMatcher(new HandlerMappingIntrospector(), "/register")
                        ))
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .sessionManagement((sessionConfig) -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling((exceptionHandlingConfig) -> exceptionHandlingConfig
                        .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler())
                );
        
        return http.build();
    }
    
    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}
