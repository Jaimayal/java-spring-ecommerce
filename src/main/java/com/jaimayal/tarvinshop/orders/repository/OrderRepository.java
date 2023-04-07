package com.jaimayal.tarvinshop.orders.repository;

import com.jaimayal.tarvinshop.AuthSystem.entity.User;
import com.jaimayal.tarvinshop.orders.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAllByUser(User user);
}
