package com.microservices.inventory_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryItem {
    private String id;
    private String productName;
    private Integer quantity;
}
