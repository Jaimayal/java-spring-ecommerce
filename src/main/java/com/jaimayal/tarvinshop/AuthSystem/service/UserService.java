package com.jaimayal.tarvinshop.AuthSystem.service;

import com.jaimayal.tarvinshop.AuthSystem.dto.UserRegisterDTO;
import com.jaimayal.tarvinshop.AuthSystem.entity.Role;
import com.jaimayal.tarvinshop.AuthSystem.entity.User;
import com.jaimayal.tarvinshop.AuthSystem.exception.PasswordDoesNotMatchException;
import com.jaimayal.tarvinshop.AuthSystem.exception.UserNotFoundException;
import com.jaimayal.tarvinshop.AuthSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class UserService {
    
    private boolean adminExists;
    
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void checkCredentials(final String email, final String password)  {
        User user = this.userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(
                        "User with email: " + email + " was not found"
                ));

        boolean passwordMatches = this.passwordEncoder.matches(password, user.getPassword());
        if (!passwordMatches) {
            throw new PasswordDoesNotMatchException("The user password is not correct");
        }
    }
    
    public void createUser(final UserRegisterDTO userRegister) {
        this.roleService.initialize();
        if (!this.adminExists) {
            this.adminExists = true;
            Collection<Role> roles = this.roleService.getAdminRoles();
            this.createUser(userRegister, roles);
            return;
        }
        
        Collection<Role> roles = this.roleService.getDefaultRoles();
        this.createUser(userRegister, roles);
    }
    
    private void createUser(final UserRegisterDTO userRegister, final Collection<Role> roles) {
        String email = userRegister.getEmail();
        
        boolean userExists = this.userRepository.existsUserByEmail(email);
        if (userExists) {
            throw new IllegalStateException("User with email: " + email + " already exists");
        }
        
        String password = this.passwordEncoder.encode(userRegister.getPassword());
        User user = new User(email, password, roles);
        this.userRepository.save(user);
    }
    
    public Collection<Role> getUserRolesByEmail(final String email) {
        User user = this.userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(
                        "User with email: " + email + " was not found"
                ));
        return user.getRoles();
    }
}
