package com.production.application.dto.request;

public record ItemOrderRequestDTO(
        Long productId,
        String description,
        String type,
        String observation
) {
}
