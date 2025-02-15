package com.production.domain.entity;

import com.production.infrastructure.persistence.entity.ProductionOrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public record Order(String orderId, ProductionOrderStatus status, LocalDateTime date, List<ItemOrder> itemsOrder) {
}
