package com.production.dtos;

import com.production.application.dto.request.ItemOrderRequestDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ItemOrderRequestDTOTest {

    @Test
    public void testItemOrderRequestDTO() {
        ItemOrderRequestDTO itemOrder = new ItemOrderRequestDTO(1L, "Product A", "Type A", "No observation");

        assertEquals(1L, itemOrder.productId());
        assertEquals("Product A", itemOrder.description());
        assertEquals("Type A", itemOrder.type());
        assertEquals("No observation", itemOrder.observation());

        assertNotNull(itemOrder.toString());

        ItemOrderRequestDTO anotherItemOrder = new ItemOrderRequestDTO(1L, "Product A", "Type A", "No observation");
        assertEquals(itemOrder, anotherItemOrder);
        assertEquals(itemOrder.hashCode(), anotherItemOrder.hashCode());
    }
}

