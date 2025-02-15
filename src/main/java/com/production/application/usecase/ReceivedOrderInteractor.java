package com.production.application.usecase;

import com.production.application.gateways.ProductionOrderGateway;
import com.production.domain.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class ReceivedOrderInteractor {

    private final ProductionOrderGateway orderGateway;

    public ReceivedOrderInteractor(ProductionOrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    public Order processOrder(Order order) {
        return orderGateway.receivedMessage(order);
    }

}
