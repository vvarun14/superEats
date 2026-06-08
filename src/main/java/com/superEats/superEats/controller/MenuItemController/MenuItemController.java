package com.superEats.superEats.controller.MenuItemController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.superEats.superEats.dto.CreateMenuItemRequest;
import com.superEats.superEats.dto.MenuItemResponse;
import com.superEats.superEats.service.MenuItemService.MenuItemService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/menu-items")
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService menuItemService;
    
    @PostMapping
    public MenuItemResponse createMenuItem(
            @Valid @RequestBody CreateMenuItemRequest request) {

        return menuItemService.createMenuItem(request);
    }

    @GetMapping
    public List<MenuItemResponse> getAllMenuItems() {
        return menuItemService.getAllMenuItems();
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<MenuItemResponse> getMenuItemsByRestaurantId(@PathVariable Long restaurantId) {
        return menuItemService.getMenuItemByRestaurant(restaurantId);
    }

    @GetMapping("/{id}")
    public MenuItemResponse getMenuItemById(@PathVariable Long id) {
        return menuItemService.getMenuItemById(id);
    }

    @PutMapping("/{id}")
    public MenuItemResponse updateMenuItem(
            @PathVariable Long id,
            @Valid @RequestBody CreateMenuItemRequest request) {

        return menuItemService.updateMenuItem(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(
            @PathVariable Long id) {

        menuItemService.deleteMenuItem(id);
        
        return ResponseEntity.noContent().build();
    }
}
