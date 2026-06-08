package com.superEats.superEats.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MenuItemResponse {
    
    private Long id;
    private String name;
    private Double price;
    private boolean isAvailable;
    private Long restaurantId;
    private String restaurantName;
}
