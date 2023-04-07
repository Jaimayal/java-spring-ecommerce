package com.jaimayal.tarvinshop.products.repository;

import com.jaimayal.tarvinshop.products.entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    List<Product> findAll();
    List<Product> findAllByIdIn(List<Long> ids);
}
