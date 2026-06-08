package com.superEats.superEats.service.impl.MenuItemServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.superEats.superEats.dto.CreateMenuItemRequest;
import com.superEats.superEats.dto.MenuItemResponse;
import com.superEats.superEats.exception.ResourceNotFoundException;
import com.superEats.superEats.model.MenuItem;
import com.superEats.superEats.model.Restaurant;
import com.superEats.superEats.repository.MenuItemRepository;
import com.superEats.superEats.repository.RestaurantRepository;
import com.superEats.superEats.service.MenuItemService.MenuItemService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {
    
    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public MenuItemResponse createMenuItem(CreateMenuItemRequest request) {

        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));

        MenuItem menuItem = new MenuItem();

        menuItem.setName(request.getName());
        menuItem.setPrice(request.getPrice());
        menuItem.setIsAvailable(true);
        menuItem.setRestaurant(restaurant);
        
        MenuItem saved = menuItemRepository.save(menuItem);

        return mapToResponse(saved);
    }

    @Override
    public List<MenuItemResponse> getAllMenuItems() {
        
        return menuItemRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<MenuItemResponse> getMenuItemByRestaurant(Long restaurantId) {

        return menuItemRepository.findByRestaurantId(restaurantId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public MenuItemResponse getMenuItemById(Long id) {

        MenuItem menuItem = menuItemRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                        "Menu item not found"));
        return mapToResponse(menuItem);
    }

    @Override
    public MenuItemResponse updateMenuItem(Long id, CreateMenuItemRequest request) {

        MenuItem menuItem = menuItemRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                        "Menu item not found"));
        
        menuItem.setName(request.getName());
        menuItem.setPrice(request.getPrice());

        MenuItem updated = menuItemRepository.save(menuItem);

        return mapToResponse(updated);
    }

    @Override
    public void deleteMenuItem(Long id) {
        MenuItem menuItem = menuItemRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException
                                        ("Menu item not found"));
                                    
        menuItemRepository.delete(menuItem);
    }

    public MenuItemResponse mapToResponse(MenuItem menuItem) {

        return MenuItemResponse.builder()
                .id(menuItem.getId())
                .name(menuItem.getName())
                .price(menuItem.getPrice())
                .isAvailable(menuItem.getIsAvailable())
                .restaurantId(menuItem.getRestaurant().getId())
                .restaurantName(menuItem.getRestaurant().getName())
                .build();
    }
}
