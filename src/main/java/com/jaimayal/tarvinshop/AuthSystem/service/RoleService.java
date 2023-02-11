package com.jaimayal.tarvinshop.AuthSystem.service;

import com.jaimayal.tarvinshop.AuthSystem.entity.Role;
import com.jaimayal.tarvinshop.AuthSystem.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void createRole(String roleName) {
        Role role = new Role(roleName);
        roleRepository.save(role);
    }
}
