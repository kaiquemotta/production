package com.production.processor;

import com.production.application.processor.SaveProductionOrderProcessor;
import com.production.application.usecase.SaveProductionOrderInteractor;
import com.production.domain.entity.ItemOrder;
import com.production.domain.entity.Order;
import com.production.infrastructure.persistence.entity.ProductionOrderStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SaveProductionOrderProcessorTest {

    @Test
    public void testProcess() {
        SaveProductionOrderInteractor saveInteractor = mock(SaveProductionOrderInteractor.class);
        ItemOrder item = new ItemOrder(1L, "Product 1", "Type A", "Observation 1");
        Order order = new Order(
                "orderId1",
                ProductionOrderStatus.RECEBIDO,  // status
                LocalDateTime.of(2025, 2, 15, 10, 30, 0),
                List.of(item)
        );
        SaveProductionOrderProcessor processor = new SaveProductionOrderProcessor(saveInteractor);
        processor.process(order);
        verify(saveInteractor).saveProductionOrder(order);
    }
}
