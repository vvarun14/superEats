package com.superEats.superEats.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOrderRequest {
    
    @NotNull
    private Long userId;

    @NotNull
    private Long restaurantId;

    private List<OrderItemRequest> items;
}
