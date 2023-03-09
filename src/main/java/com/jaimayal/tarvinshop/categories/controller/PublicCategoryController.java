package com.jaimayal.tarvinshop.categories.controller;

import com.jaimayal.tarvinshop.categories.dto.CategoryDTO;
import com.jaimayal.tarvinshop.categories.dto.CategoryProductsDTO;
import com.jaimayal.tarvinshop.categories.service.CategoryProductsService;
import com.jaimayal.tarvinshop.categories.service.CategoryService;
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
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:4200")
public class PublicCategoryController {
    private final CategoryService categoryService;
    private final CategoryProductsService categoryProductsService;
    
    @GetMapping("")
    public ResponseEntity<?> getCategories() {
        List<CategoryDTO> categories = this.categoryService.getCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable final Long id) {
        CategoryDTO category = this.categoryService.getCategory(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
    
    @GetMapping("/{id}/products")
    public ResponseEntity<?> getProductsByCategory(@PathVariable final Long id) {
        CategoryProductsDTO products = this.categoryProductsService.getProductsByCategoryId(id);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
