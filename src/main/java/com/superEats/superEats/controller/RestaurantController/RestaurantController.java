package com.superEats.superEats.controller.RestaurantController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.superEats.superEats.dto.CreateRestaurantRequest;
import com.superEats.superEats.dto.RestaurantResponse;
import com.superEats.superEats.service.RestaurantService.RestaurantService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    
    private final RestaurantService restaurantService;

    @PostMapping
    public RestaurantResponse createRestaurant(
                            @Valid @RequestBody 
                            CreateRestaurantRequest request) {
        
        return restaurantService.createRestaurant(request);
    }
    
    @GetMapping
    public List<RestaurantResponse> getAllrestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{id}")
    public RestaurantResponse getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id);
    }

    @PutMapping("/{id}")
    public RestaurantResponse updateRestaurant(
                            @PathVariable Long id,
                            @Valid @RequestBody CreateRestaurantRequest request) {
        return restaurantService.updateRestaurant(id, request);
    }
}