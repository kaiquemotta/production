package com.production.infrastructure.persistence.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemOrderEntity {

    private Long productId;
    private String description;
    private String type;
    private String observation;

}