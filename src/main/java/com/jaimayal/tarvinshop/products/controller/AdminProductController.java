package com.jaimayal.tarvinshop.products.controller;

import com.jaimayal.tarvinshop.products.dto.ProductDTO;
import com.jaimayal.tarvinshop.products.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminProductController {

    private final ProductService productService;

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> addProduct(@RequestBody final ProductDTO productDTO) {
        Long id = this.productService.addProduct(productDTO);
        return new ResponseEntity<>(Map.of("Location", "/api/products/" + id), HttpStatus.CREATED);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable final Long id) {
        this.productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable final Long id,
                                           @RequestBody final ProductDTO productDTO) {
        this.productService.updateProduct(id, productDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
