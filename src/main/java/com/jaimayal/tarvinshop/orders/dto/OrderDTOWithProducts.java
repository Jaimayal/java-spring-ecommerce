package com.jaimayal.tarvinshop.orders.dto;

import com.jaimayal.tarvinshop.products.dto.ProductDTO;
import lombok.Data;

import java.util.List;

@Data
public class OrderDTOWithProducts {
    private final Long id;
    private final List<ProductDTO> products;
}
