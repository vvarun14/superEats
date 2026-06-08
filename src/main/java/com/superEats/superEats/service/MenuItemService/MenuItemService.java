package com.superEats.superEats.service.MenuItemService;

import java.util.List;

import com.superEats.superEats.dto.CreateMenuItemRequest;
import com.superEats.superEats.dto.MenuItemResponse;

public interface MenuItemService {
    
    MenuItemResponse createMenuItem(CreateMenuItemRequest request);

    List<MenuItemResponse> getAllMenuItems();

    List<MenuItemResponse> getMenuItemByRestaurant(Long restaurantId);
    
    MenuItemResponse getMenuItemById(Long id);

    MenuItemResponse updateMenuItem(Long id, CreateMenuItemRequest request);

    void deleteMenuItem(Long id);
}
