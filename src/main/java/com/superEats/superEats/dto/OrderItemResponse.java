package com.superEats.superEats.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemResponse {
    
    private Long menuItemId;

    private String menuItemName;

    private Integer quantity;

    private Double priceSnapshot;
}
