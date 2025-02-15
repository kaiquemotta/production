package com.production.application.usecase;

import com.production.application.dto.response.ProductionOrderResponseDTO;
import com.production.application.gateways.ProductionOrderGateway;
import com.production.domain.entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllProductionOrdersInteractor {

    private final ProductionOrderGateway productionOrderGateway;

    public GetAllProductionOrdersInteractor(ProductionOrderGateway productionOrderGateway) {
        this.productionOrderGateway = productionOrderGateway;
    }

    public List<Order> getAllProductionOrders() {
        return productionOrderGateway.getAllProductionOrder();
    }
}
