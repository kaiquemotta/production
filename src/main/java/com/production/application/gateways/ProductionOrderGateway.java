package com.production.application.gateways;

import com.production.application.dto.response.ProductionOrderResponseDTO;
import com.production.domain.entity.Order;

import java.util.List;

public interface ProductionOrderGateway {

    Order receivedMessage(Order order) ;

    Order saveProductionOrder(Order order);

    List<Order> getAllProductionOrder();
}
