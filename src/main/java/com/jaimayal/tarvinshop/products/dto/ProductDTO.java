package com.jaimayal.tarvinshop.products.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private final Long id;
    private final String name;
    private final String description;
    private final BigDecimal price;
}
