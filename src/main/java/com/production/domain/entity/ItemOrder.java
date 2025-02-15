package com.production.domain.entity;

public record ItemOrder(
        Long productId,
        String description,
        String type,
        String observation
) {
}
