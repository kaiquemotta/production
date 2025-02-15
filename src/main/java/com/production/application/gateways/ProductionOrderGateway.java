package com.production.application.gateways;

import com.production.domain.entity.Order;

public interface ProductionOrderGateway {

    Order receivedMessage(Order order) ;

    Order saveProductionOrder(Order order);
}
