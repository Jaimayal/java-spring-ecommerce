package com.jaimayal.tarvinshop.AuthSystem.service;

import com.jaimayal.tarvinshop.AuthSystem.dto.UserLoginDTO;
import com.jaimayal.tarvinshop.AuthSystem.dto.UserRegisterDTO;
import com.jaimayal.tarvinshop.AuthSystem.entity.Role;
import com.jaimayal.tarvinshop.AuthSystem.entity.User;
import com.jaimayal.tarvinshop.AuthSystem.exception.UserNotFoundException;
import com.jaimayal.tarvinshop.AuthSystem.repository.RoleRepository;
import com.jaimayal.tarvinshop.AuthSystem.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(RoleRepository roleRepository, 
                       UserRepository userRepository, 
                       PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean areCredentialsValid(String email, String password)  {
        User user = this.userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(
                        "User with email: " + email + " was not found"
                ));
        return this.passwordEncoder.matches(password, user.getPassword());
    }
    
    public void createUser(UserRegisterDTO userRegister) {
        Collection<Role> roles = this.roleRepository.findAllByName("USER");
        this.createUser(userRegister, roles);
    }
    
    public void createUser(UserRegisterDTO userRegister, String role) {
        Collection<Role> roles = this.roleRepository.findAllByName(role);
        this.createUser(userRegister, roles);
    }
    
    private void createUser(UserRegisterDTO userRegister, Collection<Role> roles) {
        String email = userRegister.getEmail();
        String password = this.passwordEncoder.encode(userRegister.getPassword());
        User user = new User(email, password, roles);
        this.userRepository.save(user);
    }
    
    public Collection<Role> getUserRolesByEmail(String email) {
        User user = this.userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(
                        "User with email: " + email + " was not found"
                ));
        return user.getRoles();
    }
}