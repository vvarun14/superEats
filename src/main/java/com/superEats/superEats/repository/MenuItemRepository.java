package com.superEats.superEats.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.superEats.superEats.model.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    
    List<MenuItem> findByRestaurantId(Long restaurantId);

}
