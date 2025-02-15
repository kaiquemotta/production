package com.production.domain.entity;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemOrderTest {

    @Test
    public void testItemOrder() {
        // Cria uma instância de ItemOrder
        ItemOrder itemOrder = new ItemOrder(1L, "Description", "Type", "Observation");

        // Verifica se os valores dos campos estão corretos
        assertEquals(1L, itemOrder.productId());
        assertEquals("Description", itemOrder.description());
        assertEquals("Type", itemOrder.type());
        assertEquals("Observation", itemOrder.observation());
    }
}
