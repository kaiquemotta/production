package com.production.application.dto.response;

import com.production.domain.entity.ItemOrder;

public record ProductionItemOrderDTO(
        Long productId,
        String description,
        String type,
        String observation
) {

}