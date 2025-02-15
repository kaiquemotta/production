package com.production.application.dto.response;

import com.production.infrastructure.persistence.entity.ProductionOrderStatus;

import java.util.List;

public record ProductionOrderResponseDTO(
        String orderId,
        ProductionOrderStatus status,
        String date,
        String time,
        List<ProductionItemOrderDTO> itemsOrder) {
}


