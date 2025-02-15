package com.production.infrastructure.gateways.mapper;

import com.production.domain.entity.ItemOrder;
import com.production.infrastructure.persistence.entity.ItemOrderEntity;

public class ItemEntityMapper {

    public static ItemOrderEntity toEntity(ItemOrder itemOrder) {
        if (itemOrder == null) {
            return null;
        }
        return new ItemOrderEntity(
                itemOrder.productId(),
                itemOrder.description(),
                itemOrder.type(),
                itemOrder.observation()
        );
    }

    public static ItemOrder toDomain(ItemOrderEntity itemOrderEntity) {
        if (itemOrderEntity == null) {
            return null;
        }
        return new ItemOrder(
                itemOrderEntity.getProductId(),
                itemOrderEntity.getDescription(),
                itemOrderEntity.getType(),
                itemOrderEntity.getObservation()
        );
    }

}
