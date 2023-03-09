package com.jaimayal.tarvinshop.categories.repository;

import com.jaimayal.tarvinshop.categories.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findAll();
}
