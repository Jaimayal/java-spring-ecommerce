package com.jaimayal.tarvinshop.Product.controller;

import com.jaimayal.tarvinshop.Product.dto.ProductDTO;
import com.jaimayal.tarvinshop.Product.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "http://localhost:4200")
public class PublicProductController {
    
    private final ProductService productService;
    
    @Autowired
    public PublicProductController(final ProductService productService) {
        this.productService = productService;
    }
    
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
