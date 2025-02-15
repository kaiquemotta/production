package com.production.usecase;


import com.production.application.gateways.ProductionOrderGateway;
import com.production.application.usecase.GetAllProductionOrdersInteractor;
import com.production.domain.entity.ItemOrder;
import com.production.domain.entity.Order;
import com.production.infrastructure.persistence.entity.ProductionOrderStatus;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;

public class GetAllProductionOrdersInteractorTest {

    @Test
    public void testGetAllProductionOrders() {
        ProductionOrderGateway productionOrderGateway = mock(ProductionOrderGateway.class);
        GetAllProductionOrdersInteractor interactor = new GetAllProductionOrdersInteractor(productionOrderGateway);
        ItemOrder item = new ItemOrder(1L, "Product 1", "Type A", "Observation 1");
        Order order = new Order(
                "orderId1",
                ProductionOrderStatus.RECEBIDO,  // status
                LocalDateTime.of(2025, 2, 15, 10, 30, 0),
                List.of(item)
        );
        when(productionOrderGateway.getAllProductionOrder()).thenReturn(List.of(order));
        List<Order> orders = interactor.getAllProductionOrders();
        verify(productionOrderGateway).getAllProductionOrder();
        assert orders != null && orders.size() == 1 && orders.contains(order);
    }
}
