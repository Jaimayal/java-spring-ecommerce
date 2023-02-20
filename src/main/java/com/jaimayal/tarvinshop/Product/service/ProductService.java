package com.jaimayal.tarvinshop.Product.service;

import com.jaimayal.tarvinshop.Product.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {
    public List<ProductDTO> getProducts() {
        return List.of(
                new ProductDTO("Product 1", "Description 1", new BigDecimal("1.0")),
                new ProductDTO("Product 2", "Description 2", new BigDecimal("2.0")),
                new ProductDTO("Product 3", "Description 3", new BigDecimal("3.0"))
        );
    }

    public ProductDTO getProduct(Long id) {
        return new ProductDTO("Product 1", "Description 1", new BigDecimal("1.0"));
    }

    public Long addProduct(ProductDTO productDTO) {
        return 1L;
    }

    public void deleteProduct(Long id) {
    }

    public void updateProduct(Long id, ProductDTO productDTO) {
    }
}
