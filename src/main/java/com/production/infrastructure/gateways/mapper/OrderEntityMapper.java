package com.production.infrastructure.gateways.mapper;


import com.production.domain.entity.Order;
import com.production.infrastructure.persistence.entity.OrderEntity;

import java.util.List;
import java.util.stream.Collectors;

public class OrderEntityMapper {

    public static OrderEntity toEntity(Order order) {
        if (order == null) return null;

        return new OrderEntity(
                order.orderId(),
                order.status(),
                order.date(),
                order.itemsOrder().stream().map(ItemEntityMapper::toEntity).collect(Collectors.toList())
        );
    }

    public static Order toDomain(OrderEntity orderEntity) {
        if (orderEntity == null) return null;

        return new Order(
                orderEntity.getOrderId(),
                orderEntity.getStatus(),
                orderEntity.getDate(),
                orderEntity.getItemsOrder().stream().map(ItemEntityMapper::toDomain).collect(Collectors.toList())
        );
    }

    public static List<Order> toDomain(List<OrderEntity> list) {
        if (list == null) return null;
        return list.stream().map(OrderEntityMapper::toDomain).collect(Collectors.toList());
    }
}