package com.jaimayal.tarvinshop.orders.controller;

import com.jaimayal.tarvinshop.orders.dto.OrderDTO;
import com.jaimayal.tarvinshop.orders.dto.OrderDTOWithProducts;
import com.jaimayal.tarvinshop.orders.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
    
    private final OrderService orderService;

    @GetMapping("")
    public ResponseEntity<?> getOrders(Authentication authentication) {
        String email = authentication.getName();
        List<OrderDTO> orders = this.orderService.getOrdersByUserEmail(email);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    
    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrder(@PathVariable Long orderId, 
                                      Authentication authentication) {
        String email = authentication.getName();
        OrderDTOWithProducts order = this.orderService.getOrderByIdAndUserEmail(orderId, email);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    
    @PostMapping("")
    public ResponseEntity<?> addOrder(@RequestBody OrderDTO orderDTO, 
                                      Authentication authentication) {
        String email = authentication.getName();
        this.orderService.placeOrderForUserByEmail(orderDTO, email);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId, 
                                         Authentication authentication) {
        String email = authentication.getName();
        this.orderService.cancelOrderOfUserByEmail(orderId, email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
