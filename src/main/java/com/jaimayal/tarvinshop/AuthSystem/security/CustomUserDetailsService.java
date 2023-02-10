package com.jaimayal.tarvinshop.AuthSystem.security;

import com.jaimayal.tarvinshop.AuthSystem.entity.User;
import com.jaimayal.tarvinshop.AuthSystem.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    
    public CustomUserDetailsService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return new CustomUserDetails(user);
    }
}
