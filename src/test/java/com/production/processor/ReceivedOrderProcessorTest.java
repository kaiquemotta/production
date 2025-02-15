package com.production.processor;

import com.production.application.processor.ReceivedOrderProcessor;
import com.production.application.usecase.ReceivedOrderInteractor;
import com.production.domain.entity.ItemOrder;
import com.production.domain.entity.Order;
import com.production.infrastructure.persistence.entity.ProductionOrderStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;

public class ReceivedOrderProcessorTest {

    @Test
    public void testProcess() {
        ReceivedOrderInteractor receivedInteractor = mock(ReceivedOrderInteractor.class);
        ReceivedOrderProcessor processor = new ReceivedOrderProcessor(receivedInteractor);

        ItemOrder item = new ItemOrder(1L, "Product 1", "Type A", "Observation 1");
        Order order = new Order(
                "orderId1",
                ProductionOrderStatus.RECEBIDO,  // status
                LocalDateTime.of(2025, 2, 15, 10, 30, 0),
                List.of(item)
        );
        processor.process(order);
        verify(receivedInteractor, times(1)).processOrder(order);
    }
}
