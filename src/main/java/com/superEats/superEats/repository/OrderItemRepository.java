package com.superEats.superEats.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.superEats.superEats.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    
    List<OrderItem> findByOrderId(Long orderId);
}
