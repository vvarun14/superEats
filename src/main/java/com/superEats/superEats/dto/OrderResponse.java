package com.superEats.superEats.dto;

import java.util.List;

import com.superEats.superEats.model.enums.OrderStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponse {
    
    private Long orderId;

    private Long userId;

    private String customerName;

    private Long restaurantId;

    private String restaurantName;

    private OrderStatus status;

    private Double totalPrice;

    private String paymentStatus;

    private List<OrderItemResponse> items;

}
