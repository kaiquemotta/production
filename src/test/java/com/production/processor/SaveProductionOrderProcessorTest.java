package com.production.processor;

import com.production.application.processor.SaveProductionOrderProcessor;
import com.production.application.usecase.SaveProductionOrderInteractor;
import com.production.domain.entity.Order;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SaveProductionOrderProcessorTest {

    @Test
    public void testProcess() {
        SaveProductionOrderInteractor saveInteractor = mock(SaveProductionOrderInteractor.class);

        SaveProductionOrderProcessor processor = new SaveProductionOrderProcessor(saveInteractor);
        Order order = mock(Order.class);
        processor.process(order);
        verify(saveInteractor).saveProductionOrder(order);
    }
}
