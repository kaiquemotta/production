package com.production.application.mapper;

import com.production.application.dto.response.ProductionItemOrderDTO;
import com.production.application.dto.response.ProductionOrderResponseDTO;
import com.production.domain.entity.ItemOrder;
import com.production.domain.entity.Order;

import java.util.List;
import java.util.stream.Collectors;

public class ProductionOrderMapper {

    public static ProductionOrderResponseDTO toProductionResponse(Order order) {
        if (order == null) {
            return null;
        }

        return new ProductionOrderResponseDTO(
                order.orderId(),
                order.itemsOrder().stream()
                        .map(ProductionOrderMapper::toItemResponse)
                        .collect(Collectors.toList())
        );
    }

    private static ProductionItemOrderDTO toItemResponse(ItemOrder item) {
        if (item == null) {
            return null;
        }

        return new ProductionItemOrderDTO(
                item.productId(),
                item.description(),
                item.type(),
                item.observation()

        );
    }

    public static List<ProductionOrderResponseDTO> toProductionResponseList(List<Order> orders) {
        if (orders == null) {
            return List.of();
        }

        return orders.stream()
                .map(ProductionOrderMapper::toProductionResponse)
                .collect(Collectors.toList());
    }
}
