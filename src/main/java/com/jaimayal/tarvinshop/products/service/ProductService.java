package com.jaimayal.tarvinshop.products.service;

import com.jaimayal.tarvinshop.products.dto.ProductDTO;
import com.jaimayal.tarvinshop.products.entity.Product;
import com.jaimayal.tarvinshop.products.exception.ProductNotFoundException;
import com.jaimayal.tarvinshop.products.mapper.ProductMapper;
import com.jaimayal.tarvinshop.products.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductService {
    
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    
    public List<ProductDTO> getProducts() {
        List<Product> products = this.productRepository.findAll();
        return this.productMapper.mapToProductDTOList(products);
    }

    public ProductDTO getProduct(Long id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(
                        () -> new ProductNotFoundException("Product not found")
                );
        return this.productMapper.mapToProductDTO(product);
    }

    public Long addProduct(ProductDTO productDTO) {
        Product product = this.productMapper.mapToProduct(productDTO);
        Product savedProduct = this.productRepository.save(product);
        return savedProduct.getId();
    }

    public void deleteProduct(Long id) {
        this.productRepository.deleteById(id);
    }

    public void updateProduct(Long id, ProductDTO productDTO) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(
                        () -> new ProductNotFoundException("Product not found")
                );
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        this.productRepository.save(product);
    }
}
