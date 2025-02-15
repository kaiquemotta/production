package com.production.infrastructure.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import com.production.domain.entity.ItemOrder;

import java.util.List;

@Getter
@Setter
@Document(collection = "production")
public class OrderEntity {
    @Id
    private String id;
    private String orderId;
    private List<ItemOrderEntity> itemsOrder;

    public OrderEntity(String orderId, List<ItemOrderEntity> itemsOrder) {
        this.orderId = orderId;
        this.itemsOrder = itemsOrder;
    }
}

