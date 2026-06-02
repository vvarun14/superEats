package com.superEats.superEats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.superEats.superEats.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    
}
