package com.production.domain.repository;

import com.production.domain.entity.Order;

public interface OrderRepository {
    void sendOrder(Order order);
}
