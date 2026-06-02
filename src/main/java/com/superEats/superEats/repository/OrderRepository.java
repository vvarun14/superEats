package com.superEats.superEats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.superEats.superEats.model.Order;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}
