package com.jaimayal.tarvinshop.orders.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private final Long id;
    private final List<Long> products;
}
