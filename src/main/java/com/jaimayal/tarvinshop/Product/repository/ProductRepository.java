package com.jaimayal.tarvinshop.Product.repository;

import com.jaimayal.tarvinshop.Product.entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    List<Product> findAll();
}
