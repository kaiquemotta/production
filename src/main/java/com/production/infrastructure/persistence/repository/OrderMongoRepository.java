package com.production.infrastructure.persistence.repository;


import com.production.infrastructure.persistence.entity.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMongoRepository extends MongoRepository<OrderEntity, String> {
}