package com.production.application.dto.request;

import java.util.List;

public record ProductionOrderRequestDTO(String orderId, List<ItemOrderRequestDTO> itemsOrder) {
}