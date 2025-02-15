package com.production.application.dto.response;

import com.production.domain.entity.ItemOrder;

import java.util.List;

public record ProductionOrderResponseDTO(String orderId, List<ProductionItemOrderDTO> itemsOrder) {

}
