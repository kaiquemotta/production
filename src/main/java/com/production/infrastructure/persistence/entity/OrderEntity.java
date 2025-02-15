package com.production.infrastructure.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import com.production.domain.entity.ItemOrder;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Document(collection = "production")
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Id
    private String id;
    private String orderId;
    ProductionOrderStatus status;
    private LocalDateTime date;
    private List<ItemOrderEntity> itemsOrder;

    public OrderEntity(String orderId,ProductionOrderStatus status,LocalDateTime date, List<ItemOrderEntity> itemsOrder) {
        this.orderId = orderId;
        this.itemsOrder = itemsOrder;
    }
}

