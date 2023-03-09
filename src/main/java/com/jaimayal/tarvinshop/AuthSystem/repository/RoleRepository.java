package com.jaimayal.tarvinshop.AuthSystem.repository;

import com.jaimayal.tarvinshop.AuthSystem.entity.Role;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
    Collection<Role> findAllByName(String name);
    Boolean existsByName(String name);
}
