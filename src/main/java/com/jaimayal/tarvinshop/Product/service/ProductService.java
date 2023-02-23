package com.jaimayal.tarvinshop.Product.service;

import com.jaimayal.tarvinshop.Product.dto.ProductDTO;
import com.jaimayal.tarvinshop.Product.entity.Product;
import com.jaimayal.tarvinshop.Product.exception.ProductNotFoundException;
import com.jaimayal.tarvinshop.Product.mapper.ProductMapper;
import com.jaimayal.tarvinshop.Product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {
    
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    
    @Autowired
    public ProductService(final ProductMapper productMapper, 
                          final ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }
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
