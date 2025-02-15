package com.production.infrastructure.gateways.mapper;


import com.production.domain.entity.ItemOrder;
import com.production.infrastructure.persistence.entity.ItemOrderEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ItemEntityMapperTest {

    @Test
    public void testToEntity() {
        ItemOrder itemOrder = new ItemOrder(1L, "Product Description", "Type A", "Observation");
        ItemOrderEntity itemOrderEntity = ItemEntityMapper.toEntity(itemOrder);
        assertEquals(itemOrder.productId(), itemOrderEntity.getProductId());
        assertEquals(itemOrder.description(), itemOrderEntity.getDescription());
        assertEquals(itemOrder.type(), itemOrderEntity.getType());
        assertEquals(itemOrder.observation(), itemOrderEntity.getObservation());
    }

    @Test
    public void testToDomain() {
        ItemOrderEntity itemOrderEntity = new ItemOrderEntity(1L, "Product Description", "Type A", "Observation");
        ItemOrder itemOrder = ItemEntityMapper.toDomain(itemOrderEntity);
        assertEquals(itemOrderEntity.getProductId(), itemOrder.productId());
        assertEquals(itemOrderEntity.getDescription(), itemOrder.description());
        assertEquals(itemOrderEntity.getType(), itemOrder.type());
        assertEquals(itemOrderEntity.getObservation(), itemOrder.observation());
    }

    @Test
    public void testToEntityWithNull() {
        ItemOrderEntity itemOrderEntity = ItemEntityMapper.toEntity(null);
        assertNull(itemOrderEntity);
    }

    @Test
    public void testToDomainWithNull() {
        ItemOrder itemOrder = ItemEntityMapper.toDomain(null);
        assertNull(itemOrder);
    }
}
