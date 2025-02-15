package com.production.mapper;

import com.production.application.dto.response.ProductionItemOrderDTO;
import com.production.application.dto.response.ProductionOrderResponseDTO;
import com.production.application.mapper.ProductionOrderMapper;
import com.production.domain.entity.ItemOrder;
import com.production.domain.entity.Order;
import com.production.infrastructure.persistence.entity.ProductionOrderStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductionOrderMapperTest {

    @Test
    public void testToProductionResponse() {
        ItemOrder item = new ItemOrder(1L, "Product 1", "Type A", "Observation 1");
        Order order = new Order(
                "orderId1",
                ProductionOrderStatus.RECEBIDO,  // status
                LocalDateTime.of(2025, 2, 15, 10, 30, 0),
                List.of(item)
        );

        ProductionOrderResponseDTO responseDTO = ProductionOrderMapper.toProductionResponse(order);

        assertNotNull(responseDTO);
        assertEquals(order.orderId(), responseDTO.orderId());
        assertEquals(order.status(), responseDTO.status());
        assertEquals("2025-02-15", responseDTO.date());
        assertEquals("10:30:00", responseDTO.time());

        assertNotNull(responseDTO.itemsOrder());
        assertEquals(1, responseDTO.itemsOrder().size());

        ProductionItemOrderDTO itemDTO = responseDTO.itemsOrder().get(0);
        assertEquals(item.productId(), itemDTO.productId());
        assertEquals(item.description(), itemDTO.description());
        assertEquals(item.type(), itemDTO.type());
        assertEquals(item.observation(), itemDTO.observation());
    }

    @Test
    public void testToProductionResponseList() {
        ItemOrder item = new ItemOrder(1L, "Product 1", "Type A", "Observation 1");
        Order order = new Order(
                "orderId1",
                ProductionOrderStatus.RECEBIDO,  // status
                LocalDateTime.of(2025, 2, 15, 10, 30, 0),
                List.of(item)
        );

        List<Order> orders = List.of(order);

        List<ProductionOrderResponseDTO> responseDTOList = ProductionOrderMapper.toProductionResponseList(orders);

        assertNotNull(responseDTOList);
        assertEquals(1, responseDTOList.size());

        ProductionOrderResponseDTO responseDTO = responseDTOList.get(0);
        assertEquals(order.orderId(), responseDTO.orderId());
        assertEquals(order.status(), responseDTO.status());
    }
}
