package com.production.infrastructure.gateways.mapper;


import com.production.domain.entity.ItemOrder;
import com.production.domain.entity.Order;
import com.production.infrastructure.persistence.entity.ItemOrderEntity;
import com.production.infrastructure.persistence.entity.OrderEntity;
import com.production.infrastructure.persistence.entity.ProductionOrderStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OrderEntityMapperTest {

    @Test
    public void testToEntity() {
        Order order = new Order(
                "orderId123",
                ProductionOrderStatus.RECEBIDO,
                LocalDateTime.now(),
                List.of(new ItemOrder(1L, "Product", "Type", "Observation"))
        );
        OrderEntity orderEntity = OrderEntityMapper.toEntity(order);

        assertEquals(order.orderId(), orderEntity.getOrderId());

    }

    @Test
    public void testToDomain() {
        OrderEntity orderEntity = new OrderEntity(
                "orderId123",
                ProductionOrderStatus.RECEBIDO,
                LocalDateTime.now(),
                List.of(new ItemOrderEntity(1L, "Product", "Type", "Observation"))
        );

        Order order = OrderEntityMapper.toDomain(orderEntity);

        assertEquals(orderEntity.getOrderId(), order.orderId());
        assertEquals(orderEntity.getStatus(), order.status());
        assertEquals(orderEntity.getDate(), order.date());
        assertEquals(orderEntity.getItemsOrder().size(), order.itemsOrder().size());
    }

    @Test
    public void testToEntityWithNull() {
        OrderEntity orderEntity = OrderEntityMapper.toEntity(null);

        assertNull(orderEntity);
    }

    @Test
    public void testToDomainWithNull() {
        Order order = OrderEntityMapper.toDomain((OrderEntity) null);
        assertNull(order);
    }

    @Test
    public void testToDomainList() {
        List<OrderEntity> orderEntities = List.of(
                new OrderEntity("orderId123", ProductionOrderStatus.RECEBIDO, LocalDateTime.now(), List.of(new ItemOrderEntity(1L, "Product", "Type", "Observation"))),
                new OrderEntity("orderId124", ProductionOrderStatus.RECEBIDO, LocalDateTime.now(), List.of(new ItemOrderEntity(2L, "Product2", "Type2", "Observation2")))
        );
        List<Order> orders = OrderEntityMapper.toDomain(orderEntities);
        assertEquals(orderEntities.size(), orders.size());
    }

    @Test
    public void testToDomainListWithNull() {
        List<Order> orders = OrderEntityMapper.toDomain((List<OrderEntity>) null);
        assertNull(orders);
    }
}
