package com.superEats.superEats.service.RestaurantService;

import java.util.List;

import com.superEats.superEats.dto.CreateRestaurantRequest;
import com.superEats.superEats.dto.RestaurantResponse;

public interface RestaurantService {
    
    RestaurantResponse createRestaurant(CreateRestaurantRequest request);

    List<RestaurantResponse> getAllRestaurants();

    RestaurantResponse getRestaurantById(Long id);

    RestaurantResponse updateRestaurant(Long id, CreateRestaurantRequest request);
}
