package com.production.infrastructure.repository;


import com.production.domain.entity.Order;
import com.production.domain.repository.OrderRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderMongoRepository extends OrderRepository, MongoRepository<Order, String> {
}
