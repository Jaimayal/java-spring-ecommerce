package com.jaimayal.tarvinshop.AuthSystem.service;

import com.jaimayal.tarvinshop.AuthSystem.entity.Role;
import com.jaimayal.tarvinshop.AuthSystem.repository.RoleRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@AllArgsConstructor
@Service
public class RoleService {
    
    private final RoleRepository roleRepository;

    public void createRole(final String roleName) {
        Role role = new Role(roleName);
        roleRepository.save(role);
    }
    
    public void initialize() {
        this.createRole("USER");
        this.createRole("ADMIN");
    }

    public Collection<Role> getDefaultRoles() {
        return this.roleRepository.findAllByName("USER");
    }

    public Collection<Role> getAdminRoles() {
        return this.roleRepository.findAllByName("ADMIN");
    }
}
