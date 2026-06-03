package com.superEats.superEats.service.impl.RestaurantServiceImpl;

import org.springframework.stereotype.Service;

import com.superEats.superEats.dto.CreateRestaurantRequest;
import com.superEats.superEats.dto.RestaurantResponse;
import com.superEats.superEats.model.Restaurant;
import com.superEats.superEats.repository.RestaurantRepository;
import com.superEats.superEats.service.RestaurantService.RestaurantService;
import com.superEats.superEats.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService{

    private final RestaurantRepository restaurantRepository;

    @Override
    public RestaurantResponse createRestaurant(CreateRestaurantRequest request) {
        
        Restaurant restaurant = new Restaurant();

        restaurant.setName(request.getName());
        restaurant.setAddress(request.getAddress());
        restaurant.setLatitude(request.getLatitude());
        restaurant.setLongitude(request.getLongitude());
        restaurant.setIsOpen(true);

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return mapToResponse(savedRestaurant);
    }

    @Override
    public List<RestaurantResponse> getAllRestaurants() {
        return restaurantRepository.findAll().
            stream().
            map(this::mapToResponse).
            toList();
    }

    @Override
    public RestaurantResponse getRestaurantById(Long id) {

        Restaurant restaurant =
                restaurantRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Restaurant not found with id: " + id));

        return mapToResponse(restaurant);
    }

    
    @Override
    public RestaurantResponse updateRestaurant(
            Long id,
            CreateRestaurantRequest request) {

        Restaurant restaurant =
                restaurantRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Restaurant not found with id: " + id));

        restaurant.setName(request.getName());
        restaurant.setAddress(request.getAddress());
        restaurant.setLatitude(request.getLatitude());
        restaurant.setLongitude(request.getLongitude());

        Restaurant updatedRestaurant =
                restaurantRepository.save(restaurant);

        return mapToResponse(updatedRestaurant);
    }

    private RestaurantResponse mapToResponse(Restaurant restaurant) {
        
        return RestaurantResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .latitude(restaurant.getLatitude())
                .longitude(restaurant.getLongitude())
                .isOpen(restaurant.getIsOpen())
                .build();
    }
}
