package com.jaimayal.tarvinshop.categories.dto;

import com.jaimayal.tarvinshop.products.dto.ProductDTO;
import lombok.Data;

import java.util.List;

@Data
public class CategoryProductsDTO {
    private List<ProductDTO> products;
}
