package com.production.usecase;


import com.production.application.gateways.ProductionOrderGateway;
import com.production.application.usecase.ReceivedOrderInteractor;
import com.production.domain.entity.ItemOrder;
import com.production.domain.entity.Order;
import com.production.infrastructure.persistence.entity.ProductionOrderStatus;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;

public class ReceivedOrderInteractorTest {

    @Test
    public void testProcessOrder() {
        ProductionOrderGateway productionOrderGateway = mock(ProductionOrderGateway.class);
        ReceivedOrderInteractor interactor = new ReceivedOrderInteractor(productionOrderGateway);
        ItemOrder item = new ItemOrder(1L, "Product 1", "Type A", "Observation 1");
        Order order = new Order(
                "orderId1",
                ProductionOrderStatus.RECEBIDO,  // status
                LocalDateTime.of(2025, 2, 15, 10, 30, 0),
                List.of(item)
        );
        when(productionOrderGateway.receivedMessage(order)).thenReturn(order);
        Order processedOrder = interactor.processOrder(order);
        verify(productionOrderGateway).receivedMessage(order);
        assert processedOrder == order;
    }
}
