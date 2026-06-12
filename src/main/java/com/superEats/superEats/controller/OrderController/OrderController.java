package com.superEats.superEats.controller.OrderController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.superEats.superEats.dto.CreateOrderRequest;
import com.superEats.superEats.dto.OrderResponse;
import com.superEats.superEats.model.enums.OrderStatus;
import com.superEats.superEats.service.OrderService.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    
    private final OrderService orderService;

    @PostMapping
    public OrderResponse createOrder(
                    @Valid @RequestBody CreateOrderRequest request) {
        
        return orderService.createOrder(request);
    }

    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable Long id) {

        return orderService.getOrderById(id);
    }

    @GetMapping("/user/{userId}")
    public List<OrderResponse> getOrderByUser(@PathVariable Long userId) {
        
        return orderService.getOrderByUser(userId);
    }

    @PutMapping("/{id}/status")
    public OrderResponse updateOrderStatus(@PathVariable Long id, 
                                            @RequestParam OrderStatus status) {
        
        return orderService.updateOrderStatus(id, status);
    }
}
