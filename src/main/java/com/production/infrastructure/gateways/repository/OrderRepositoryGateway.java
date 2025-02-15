package com.production.infrastructure.gateways.repository;

import com.production.application.gateways.ProductionOrderGateway;
import com.production.domain.entity.Order;
import com.production.infrastructure.gateways.mapper.OrderEntityMapper;
import com.production.infrastructure.persistence.entity.OrderEntity;
import com.production.infrastructure.persistence.repository.OrderMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class OrderRepositoryGateway implements ProductionOrderGateway {

    @Autowired
    private  OrderMongoRepository orderRepository;



    @Override
    public Order receivedMessage(Order order) {
//        OrderEntity orderEntity = OrderEntityMapper.toEntity(order);
//        orderRepository.save(orderEntity);
        return null;
    }

    @Override
    public Order saveProductionOrder(Order order) {
        OrderEntity orderEntity = OrderEntityMapper.toEntity(order);
        OrderEntity op = orderRepository.save(orderEntity);
        return OrderEntityMapper.toDomain(op);
    }
}
