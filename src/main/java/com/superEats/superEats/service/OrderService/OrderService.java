package com.superEats.superEats.service.OrderService;

import java.util.List;

import com.superEats.superEats.dto.CreateOrderRequest;
import com.superEats.superEats.dto.OrderResponse;
import com.superEats.superEats.model.enums.OrderStatus;

public interface OrderService {
    
    OrderResponse createOrder(CreateOrderRequest request);

    OrderResponse getOrderById(Long id);

    List<OrderResponse> getOrderByUser(Long userId);

    OrderResponse updateOrderStatus(Long orderId, OrderStatus status);
}
