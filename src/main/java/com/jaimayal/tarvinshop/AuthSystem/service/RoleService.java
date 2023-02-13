package com.jaimayal.tarvinshop.AuthSystem.service;

import com.jaimayal.tarvinshop.AuthSystem.entity.Role;
import com.jaimayal.tarvinshop.AuthSystem.repository.RoleRepository;

import org.springframework.stereotype.Service;

@Service
public class RoleService {
    
    private final RoleRepository roleRepository;

    public RoleService(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void createRole(final String roleName) {
        Role role = new Role(roleName);
        roleRepository.save(role);
    }
}
