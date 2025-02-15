package com.production.application.usecase;

import com.production.application.gateways.ProductionOrderGateway;
import com.production.domain.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class SaveProductionOrderInteractor {

    private final ProductionOrderGateway orderGateway;

    public SaveProductionOrderInteractor(ProductionOrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    public Order saveProductionOrder(Order order) {
        return orderGateway.saveProductionOrder(order);
    }
}
