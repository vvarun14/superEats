package com.superEats.superEats.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.superEats.superEats.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    
}
