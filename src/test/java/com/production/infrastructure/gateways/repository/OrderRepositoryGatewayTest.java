package com.production.infrastructure.gateways.repository;

import com.production.domain.entity.ItemOrder;
import com.production.domain.entity.Order;
import com.production.infrastructure.gateways.mapper.OrderEntityMapper;
import com.production.infrastructure.persistence.entity.ItemOrderEntity;
import com.production.infrastructure.persistence.entity.OrderEntity;
import com.production.infrastructure.persistence.entity.ProductionOrderStatus;
import com.production.infrastructure.persistence.repository.OrderMongoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderRepositoryGatewayTest {

    @Mock
    private OrderMongoRepository orderRepository;

    @InjectMocks
    private OrderRepositoryGateway orderRepositoryGateway;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveProductionOrder() {

        ItemOrder item1 = new ItemOrder(1L, "Product 1", "Type A", "Observation 1");
        Order order = new Order("orderId123", ProductionOrderStatus.RECEBIDO, LocalDateTime.now(), List.of(item1));

        OrderEntity orderEntity = new OrderEntity(
                "orderId123",
                ProductionOrderStatus.RECEBIDO,
                LocalDateTime.now(),
                List.of(new ItemOrderEntity(1L, "Product", "Type", "Observation"))
        );
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);
        Order savedOrder = orderRepositoryGateway.saveProductionOrder(order);
        verify(orderRepository).save(any(OrderEntity.class));
        assertNotNull(savedOrder);
        assertEquals(order.orderId(), savedOrder.orderId());
    }

    @Test
    public void testGetAllProductionOrder() {
        OrderEntity orderEntity1 = new OrderEntity("orderId123", ProductionOrderStatus.RECEBIDO, LocalDateTime.now(), List.of(new ItemOrderEntity(1L, "Product", "Type", "Observation"))
        );
        OrderEntity orderEntity2 = new OrderEntity("orderId124", ProductionOrderStatus.RECEBIDO, LocalDateTime.now(), List.of(new ItemOrderEntity(1L, "Product", "Type", "Observation"))
        );
        List<OrderEntity> orderEntityList = List.of(orderEntity1, orderEntity2);
        when(orderRepository.findAll()).thenReturn(orderEntityList);
        List<Order> orders = orderRepositoryGateway.getAllProductionOrder();
        verify(orderRepository).findAll();
        assertNotNull(orders);
        assertEquals(2, orders.size());
        assertEquals(orderEntity1.getOrderId(), orders.get(0).orderId());
        assertEquals(orderEntity2.getOrderId(), orders.get(1).orderId());
    }
}
