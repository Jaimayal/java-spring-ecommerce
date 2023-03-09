package com.jaimayal.tarvinshop.products.controller;

import com.jaimayal.tarvinshop.products.dto.ProductDTO;
import com.jaimayal.tarvinshop.products.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:4200")
public class PublicProductController {
    
    private final ProductService productService;
    
    @GetMapping("")
    public ResponseEntity<?> getProducts() {
        List<ProductDTO> products = this.productService.getProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable final Long id) {
        ProductDTO product = this.productService.getProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
