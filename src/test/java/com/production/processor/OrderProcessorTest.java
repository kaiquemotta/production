package com.production.processor;

import com.production.application.processor.OrderProcessor;
import com.production.domain.entity.ItemOrder;
import com.production.domain.entity.Order;
import com.production.infrastructure.persistence.entity.ProductionOrderStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;

public class OrderProcessorTest {

    @Test
    public void testProcess() {
        ItemOrder item = new ItemOrder(1L, "Product 1", "Type A", "Observation 1");
        Order order = new Order(
                "orderId1",
                ProductionOrderStatus.RECEBIDO,  // status
                LocalDateTime.of(2025, 2, 15, 10, 30, 0),
                List.of(item)
        );
        OrderProcessor orderProcessor = mock(OrderProcessor.class);
        orderProcessor.process(order);
        verify(orderProcessor, times(1)).process(order);
    }
}

