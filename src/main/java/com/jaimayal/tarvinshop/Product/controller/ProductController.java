package com.jaimayal.tarvinshop.Product.controller;

import com.jaimayal.tarvinshop.Product.dto.ProductDTO;
import com.jaimayal.tarvinshop.Product.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    
    private final ProductService productService;
    
    @Autowired
    public ProductController(final ProductService productService) {
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
    
    @PostMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addProduct(@RequestBody final ProductDTO productDTO) {
        Long id = this.productService.addProduct(productDTO);
        return new ResponseEntity<>(Map.of("Location", "/api/product/" + id), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteProduct(@PathVariable final Long id) {
        this.productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateProduct(@PathVariable final Long id, 
                                           @RequestBody final ProductDTO productDTO) {
        this.productService.updateProduct(id, productDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
