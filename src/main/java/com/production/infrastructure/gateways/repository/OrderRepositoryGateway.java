package com.production.infrastructure.gateways.repository;

import com.production.application.dto.response.ProductionOrderResponseDTO;
import com.production.application.gateways.ProductionOrderGateway;
import com.production.domain.entity.Order;
import com.production.infrastructure.gateways.mapper.OrderEntityMapper;
import com.production.infrastructure.persistence.entity.OrderEntity;
import com.production.infrastructure.persistence.entity.ProductionOrderStatus;
import com.production.infrastructure.persistence.repository.OrderMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;


@Component
public class OrderRepositoryGateway implements ProductionOrderGateway {

    @Autowired
    private  OrderMongoRepository orderRepository;

    @Override
    public Order receivedMessage(Order order) {

        return null;
    }

    @Override
    public Order saveProductionOrder(Order order) {
        OrderEntity orderEntity = OrderEntityMapper.toEntity(order);
        orderEntity.setStatus(ProductionOrderStatus.RECEBIDO);
        orderEntity.setDate(LocalDateTime.now());
        OrderEntity op = orderRepository.save(orderEntity);
        return OrderEntityMapper.toDomain(op);
    }

    @Override
    public List<Order> getAllProductionOrder() {
        List<OrderEntity> list = orderRepository.findAll();
        List<Order> orders =  OrderEntityMapper.toDomain(list);
        return orders;
    }
}
