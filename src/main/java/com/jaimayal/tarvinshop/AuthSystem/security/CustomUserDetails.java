package com.jaimayal.tarvinshop.AuthSystem.security;

import com.jaimayal.tarvinshop.AuthSystem.entity.Role;
import com.jaimayal.tarvinshop.AuthSystem.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {
    private final User user;
    
    public CustomUserDetails(User user) {
        this.user = user;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getAuthorities(this.user.getRoles());
    }
    
    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return roles.stream().map(Role::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
