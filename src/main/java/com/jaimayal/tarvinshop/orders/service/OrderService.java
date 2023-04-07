package com.jaimayal.tarvinshop.orders.service;

import com.jaimayal.tarvinshop.AuthSystem.entity.User;
import com.jaimayal.tarvinshop.AuthSystem.exception.UserNotFoundException;
import com.jaimayal.tarvinshop.AuthSystem.repository.UserRepository;
import com.jaimayal.tarvinshop.orders.dto.OrderDTO;
import com.jaimayal.tarvinshop.orders.dto.OrderDTOWithProducts;
import com.jaimayal.tarvinshop.orders.entity.Order;
import com.jaimayal.tarvinshop.orders.exception.OrderNotFoundException;
import com.jaimayal.tarvinshop.orders.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderService {
    
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    
    public List<OrderDTO> getOrdersByUserEmail(String email) {
        User user = this.getUserByEmail(email);
        
        List<Order> userOrders = this.orderRepository.findAllByUser(user);
        return userOrders;
    }
    
    public OrderDTOWithProducts getOrderByIdAndUserEmail(Long orderId, String email) {
        User user = this.getUserByEmail(email);
        Order order = this.orderRepository.findById(orderId)
                .orElseThrow(
                        () -> new OrderNotFoundException("Order not found")
                );
    }

    public void placeOrderForUserByEmail(OrderDTO orderDTO, String email) {
    }

    public void cancelOrderOfUserByEmail(Long orderId, String email) {
    }
    
    private User getUserByEmail(String email) {
        return this.userRepository.findUserByEmail(email)
                .orElseThrow(
                        () -> new UserNotFoundException("User not found")
                );
    }
}
