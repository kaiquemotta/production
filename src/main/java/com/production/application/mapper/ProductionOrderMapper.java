package com.production.application.mapper;

import com.production.application.dto.response.ProductionItemOrderDTO;
import com.production.application.dto.response.ProductionOrderResponseDTO;
import com.production.domain.entity.ItemOrder;
import com.production.domain.entity.Order;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProductionOrderMapper {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static ProductionOrderResponseDTO toProductionResponse(Order order) {
        if (order == null) return null;

        LocalDateTime orderDate = order.date();
        String date = orderDate != null ? orderDate.format(DATE_FORMATTER) : null;
        String time = orderDate != null ? orderDate.format(TIME_FORMATTER) : null;

        return new ProductionOrderResponseDTO(
                order.orderId(),
                order.status(),
                date,
                time,
                order.itemsOrder().stream()
                        .map(ProductionOrderMapper::toItemResponse)
                        .collect(Collectors.toList())
        );
    }

    private static ProductionItemOrderDTO toItemResponse(ItemOrder item) {
        if (item == null) return null;

        return new ProductionItemOrderDTO(
                item.productId(),
                item.description(),
                item.type(),
                item.observation()
        );
    }

    public static List<ProductionOrderResponseDTO> toProductionResponseList(List<Order> orders) {
        if (orders == null) return List.of();

        return orders.stream()
                .map(ProductionOrderMapper::toProductionResponse)
                .collect(Collectors.toList());
    }
}

