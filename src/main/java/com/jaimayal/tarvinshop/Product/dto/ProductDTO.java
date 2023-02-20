package com.jaimayal.tarvinshop.Product.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private final String name;
    private final String description;
    private final BigDecimal price;
}
