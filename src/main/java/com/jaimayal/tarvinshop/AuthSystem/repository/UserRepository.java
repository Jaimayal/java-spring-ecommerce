package com.jaimayal.tarvinshop.AuthSystem.repository;

import com.jaimayal.tarvinshop.AuthSystem.entity.User;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    
    Optional<User> findUserById(Long id);
    Optional<User> findUserByEmail(String email);
    boolean existsUserByEmail(String email);
}
