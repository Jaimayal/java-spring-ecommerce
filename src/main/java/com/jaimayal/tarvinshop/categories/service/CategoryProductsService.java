package com.jaimayal.tarvinshop.categories.service;

import com.jaimayal.tarvinshop.categories.dto.CategoryProductsDTO;
import com.jaimayal.tarvinshop.categories.dto.CategoryProductsIdsDTO;
import com.jaimayal.tarvinshop.categories.entity.Category;
import com.jaimayal.tarvinshop.categories.exception.CategoryNotFoundException;
import com.jaimayal.tarvinshop.categories.mapper.CategoryMapper;
import com.jaimayal.tarvinshop.categories.repository.CategoryRepository;
import com.jaimayal.tarvinshop.products.entity.Product;
import com.jaimayal.tarvinshop.products.exception.ProductNotFoundException;
import com.jaimayal.tarvinshop.products.repository.ProductRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CategoryProductsService {
    
    private final ProductRepository productRepository;
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryProductsDTO getProductsByCategoryId(final Long id) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(
                        () -> new CategoryNotFoundException("Category not found")
                );
        
        CategoryProductsDTO categoryProducts = this.categoryMapper.mapToCategoryProductsDTO(category);
        return categoryProducts;
    }

    public void addProductsByProductIdsToCategoryByCategoryId(final Long id, 
                                                              final CategoryProductsIdsDTO categoryProductsIdsDTO) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(
                        () -> new CategoryNotFoundException("Category not found")
                );
        List<Long> productIds = categoryProductsIdsDTO.getProductIds();
        List<Product> products = this.productRepository.findAllByIdIn(productIds);
        
        if (products.size() != productIds.size()) {
            List<Long> notFoundProductIds = productIds.stream()
                    .filter(productId -> products.stream()
                            .noneMatch(product -> product.getId().equals(productId)))
                    .collect(Collectors.toList());
            throw new ProductNotFoundException("The following productIds do not exist: " + notFoundProductIds);
        }
        
        category.addProducts(products);
        this.categoryRepository.save(category);
    }

    public void addProductByProductIdToCategoryByCategoryId(final Long categoryId, 
                                                            final Long productId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(
                        () -> new CategoryNotFoundException("Category not found")
                );

        Product product = this.productRepository.findById(productId)
                .orElseThrow(
                        () -> new ProductNotFoundException("Product not found")
                );

        category.addProduct(product);
        this.categoryRepository.save(category);
    }
}
