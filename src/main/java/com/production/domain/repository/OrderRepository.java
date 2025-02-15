package com.production.domain.repository;

import com.production.domain.entity.Order;

import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);
    Optional<Order> findById(String orderId);

}
