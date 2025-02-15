package com.production.dtos;

import com.production.application.dto.request.ItemOrderRequestDTO;
import com.production.application.dto.request.ProductionOrderRequestDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductionOrderRequestDTOTest {

    @Test
    public void testProductionOrderRequestDTO() {
        ItemOrderRequestDTO item1 = new ItemOrderRequestDTO(1L, "Product A", "Type A", "No observation");
        ItemOrderRequestDTO item2 = new ItemOrderRequestDTO(2L, "Product B", "Type B", "Special observation");
        List<ItemOrderRequestDTO> itemsOrder = List.of(item1, item2);

        ProductionOrderRequestDTO productionOrder = new ProductionOrderRequestDTO("Order123", itemsOrder);

        assertEquals("Order123", productionOrder.orderId());

        assertNotNull(productionOrder.itemsOrder());
        assertEquals(2, productionOrder.itemsOrder().size());

        assertEquals(item1, productionOrder.itemsOrder().get(0));
        assertEquals(item2, productionOrder.itemsOrder().get(1));

        assertNotNull(productionOrder.toString());

        ProductionOrderRequestDTO anotherProductionOrder = new ProductionOrderRequestDTO("Order123", itemsOrder);
        assertEquals(productionOrder, anotherProductionOrder);
        assertEquals(productionOrder.hashCode(), anotherProductionOrder.hashCode());
    }
}
