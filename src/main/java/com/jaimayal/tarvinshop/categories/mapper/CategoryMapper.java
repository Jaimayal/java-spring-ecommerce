package com.jaimayal.tarvinshop.categories.mapper;

import com.jaimayal.tarvinshop.categories.dto.CategoryDTO;
import com.jaimayal.tarvinshop.categories.dto.CategoryProductsDTO;
import com.jaimayal.tarvinshop.categories.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CategoryMapper {
    CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);
    
    List<CategoryDTO> mapToCategoryDTOList(List<Category> categoryList);
    CategoryDTO mapToCategoryDTO(Category category);
    List<Category> mapToCategoryList(List<CategoryDTO> categoryDTOList);
    Category mapToCategory(CategoryDTO categoryDTO);
    CategoryProductsDTO mapToCategoryProductsDTO(Category category);
}
