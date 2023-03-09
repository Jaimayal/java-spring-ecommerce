package com.jaimayal.tarvinshop.categories.service;

import com.jaimayal.tarvinshop.categories.dto.CategoryDTO;
import com.jaimayal.tarvinshop.categories.entity.Category;
import com.jaimayal.tarvinshop.categories.exception.CategoryNotFoundException;
import com.jaimayal.tarvinshop.categories.mapper.CategoryMapper;
import com.jaimayal.tarvinshop.categories.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoryService {
    
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;
    
    public List<CategoryDTO> getCategories() {
        List<Category> categories = this.categoryRepository.findAll();
        return this.categoryMapper.mapToCategoryDTOList(categories);
    }

    public CategoryDTO getCategory(final Long id) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(
                        () -> new CategoryNotFoundException("Category not found")
                );
        return this.categoryMapper.mapToCategoryDTO(category);
    }

    public Long addCategory(final CategoryDTO categoryDTO) {
        Category category = this.categoryMapper.mapToCategory(categoryDTO);
        Category savedCategory = this.categoryRepository.save(category);
        return savedCategory.getId();
    }

    public void deleteCategory(final Long id) {
        this.categoryRepository.deleteById(id);
    }

    public void updateCategory(final Long id, final CategoryDTO categoryDTO) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(
                        () -> new CategoryNotFoundException("Category not found")
                );
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        this.categoryRepository.save(category);
    }
}
