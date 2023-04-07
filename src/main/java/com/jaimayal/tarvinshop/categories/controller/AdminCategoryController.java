package com.jaimayal.tarvinshop.categories.controller;

import com.jaimayal.tarvinshop.categories.dto.CategoryDTO;
import com.jaimayal.tarvinshop.categories.dto.CategoryProductsIdsDTO;
import com.jaimayal.tarvinshop.categories.service.CategoryProductsService;
import com.jaimayal.tarvinshop.categories.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminCategoryController {
    
    private final CategoryService categoryService;
    private final CategoryProductsService categoryProductsService;

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> addCategory(@RequestBody final CategoryDTO categoryDTO) {
        Long id = this.categoryService.addCategory(categoryDTO);
        return new ResponseEntity<>(Map.of("Location", "/api/categories/" + id), HttpStatus.CREATED);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable final Long id) {
        this.categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable final Long id,
                                            @RequestBody CategoryDTO categoryDTO) {
        this.categoryService.updateCategory(id, categoryDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
//    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{categoryId}/products/{productId}")
    public ResponseEntity<?> addProductToCategory(@PathVariable final Long categoryId, 
                                                  @PathVariable final Long productId) {
        this.categoryProductsService.addProductByProductIdToCategoryByCategoryId(categoryId, productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
//    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{categoryId}/products")
    public ResponseEntity<?> addProductsToCategory(@PathVariable final Long categoryId, 
                                                   @RequestBody final CategoryProductsIdsDTO productIds) {
        this.categoryProductsService.addProductsByProductIdsToCategoryByCategoryId(categoryId, productIds);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
