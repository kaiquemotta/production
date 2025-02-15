package com.production.domain.entity;

import java.util.List;

public record Order(String orderId, List<ItemOrder> itemsOrder) {
}