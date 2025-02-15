package com.production.domain;


import com.production.domain.entity.ItemOrder;
import com.production.domain.entity.Order;
import com.production.infrastructure.persistence.entity.ProductionOrderStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {

    @Test
    public void testOrder() {
        ItemOrder itemOrder1 = new ItemOrder(1L, "Description1", "Type1", "Observation1");
        ItemOrder itemOrder2 = new ItemOrder(2L, "Description2", "Type2", "Observation2");
        Order order = new Order("order123", ProductionOrderStatus.RECEBIDO, LocalDateTime.now(), List.of(itemOrder1, itemOrder2));
        assertEquals("order123", order.orderId());
        assertEquals(ProductionOrderStatus.RECEBIDO, order.status());
        assertEquals(2, order.itemsOrder().size());
        assertEquals(itemOrder1, order.itemsOrder().get(0));
        assertEquals(itemOrder2, order.itemsOrder().get(1));
    }
}
