package com.jaimayal.tarvinshop.products.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private final Long id;
    private final String name;
    private final String description;
    private final BigDecimal price;
}
