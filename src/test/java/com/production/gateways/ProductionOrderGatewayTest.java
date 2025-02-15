package com.production.gateways;

import com.production.application.gateways.ProductionOrderGateway;
import com.production.domain.entity.ItemOrder;
import com.production.domain.entity.Order;
import com.production.infrastructure.persistence.entity.ProductionOrderStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductionOrderGatewayTest {

    @Test
    public void testSaveProductionOrder() {
        ProductionOrderGateway productionOrderGateway = mock(ProductionOrderGateway.class);
        ItemOrder item1 = new ItemOrder(1L, "Product 1", "Type A", "Observation 1");
        ItemOrder item2 = new ItemOrder(2L, "Product 2", "Type B", "Observation 2");
        Order order = new Order(
                "123",
                ProductionOrderStatus.RECEBIDO,
                LocalDateTime.now(),
                List.of(item1, item2)
        );

        when(productionOrderGateway.saveProductionOrder(order)).thenReturn(order);
        Order savedOrder = productionOrderGateway.saveProductionOrder(order);
        assertEquals(order, savedOrder);
        verify(productionOrderGateway, times(1)).saveProductionOrder(order);
    }

    @Test
    public void testGetAllProductionOrder() {
        ProductionOrderGateway productionOrderGateway = mock(ProductionOrderGateway.class);
        ItemOrder item1 = new ItemOrder(1L, "Product 1", "Type A", "Observation 1");
        ItemOrder item2 = new ItemOrder(2L, "Product 2", "Type B", "Observation 2");
        Order order = new Order(
                "123",
                ProductionOrderStatus.RECEBIDO,
                LocalDateTime.now(),
                List.of(item1, item2)
        );
        List<Order> orders = Collections.singletonList(order);
        when(productionOrderGateway.getAllProductionOrder()).thenReturn(orders);
        List<Order> result = productionOrderGateway.getAllProductionOrder();
        assertEquals(orders, result);
        verify(productionOrderGateway, times(1)).getAllProductionOrder();
    }

    @Test
    public void testReceivedMessage() {
        ProductionOrderGateway productionOrderGateway = mock(ProductionOrderGateway.class);
        ItemOrder item1 = new ItemOrder(1L, "Product 1", "Type A", "Observation 1");
        ItemOrder item2 = new ItemOrder(2L, "Product 2", "Type B", "Observation 2");
        Order order = new Order(
                "123",
                ProductionOrderStatus.RECEBIDO,
                LocalDateTime.now(),
                List.of(item1, item2)
        );
        when(productionOrderGateway.receivedMessage(order)).thenReturn(order);
        Order receivedOrder = productionOrderGateway.receivedMessage(order);
        assertEquals(order, receivedOrder);
        verify(productionOrderGateway, times(1)).receivedMessage(order);
    }
}

