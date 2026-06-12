package com.superEats.superEats.service.impl.OrderServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.superEats.superEats.dto.CreateOrderRequest;
import com.superEats.superEats.dto.OrderItemRequest;
import com.superEats.superEats.dto.OrderItemResponse;
import com.superEats.superEats.dto.OrderResponse;
import com.superEats.superEats.exception.ResourceNotFoundException;
import com.superEats.superEats.model.MenuItem;
import com.superEats.superEats.model.Order;
import com.superEats.superEats.model.OrderItem;
import com.superEats.superEats.model.Restaurant;
import com.superEats.superEats.model.User;
import com.superEats.superEats.model.enums.OrderStatus;
import com.superEats.superEats.repository.MenuItemRepository;
import com.superEats.superEats.repository.OrderItemRepository;
import com.superEats.superEats.repository.OrderRepository;
import com.superEats.superEats.repository.RestaurantRepository;
import com.superEats.superEats.repository.UserRepository;
import com.superEats.superEats.service.OrderService.OrderService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final MenuItemRepository menuItemRepository;


    /* 
        What createOrder() does:
        
        Finds the user.
        Finds the restaurant.
        Validates every menu item exists.
        Validates every menu item belongs to the selected restaurant.
        Calculates the total order price.
        Creates and saves the Order.
        Creates and saves all OrderItems.
        Returns a complete OrderResponse.

        Because of @Transactional, if any step fails, nothing is saved to the database. 
    */

    @Override
    @Transactional
    public OrderResponse createOrder(CreateOrderRequest request) {
        
        User user = userRepository.findById(
                        request.getUserId())
                        .orElseThrow(() -> new ResourceNotFoundException(
                            "User not found"));

        Restaurant restaurant = restaurantRepository.findById(
                                    request.getRestaurantId())
                                    .orElseThrow(() -> new ResourceNotFoundException(
                                        "Restaurant not found"));
        
        double totalPrice = 0.0;

        for(OrderItemRequest itemRequest : request.getItems()) {

            MenuItem menuItem = menuItemRepository.findById(itemRequest.getMenuItemId())
                                        .orElseThrow(() -> new ResourceNotFoundException(
                                            "Menu item not found"));

            if(!menuItem.getRestaurant().getId().equals(restaurant.getId())) {
                throw new IllegalArgumentException("Menu item does not belong to the selected restaurant");
            }

            totalPrice += menuItem.getPrice() * itemRequest.getQuantity();
        }

        Order order = new Order();

        order.setUser(user);
        order.setRestaurant(restaurant);
        order.setStatus(OrderStatus.PENDING);
        order.setPaymentStatus("PENDING");
        order.setTotalPrice(totalPrice);

        Order savedOrder = orderRepository.save(order);

        for(OrderItemRequest itemRequest : request.getItems()) {

            MenuItem menuItem = menuItemRepository.findById(itemRequest.getMenuItemId())
                                        .orElseThrow(() -> new ResourceNotFoundException(
                                            "Menu item not found"));

            OrderItem orderItem = new OrderItem();

            orderItem.setOrder(savedOrder);
            orderItem.setMenuItem(menuItem);
            orderItem.setQuantity(itemRequest.getQuantity());

            orderItem.setPriceSnapshot(menuItem.getPrice());
            
            orderItemRepository.save(orderItem);
        }

        return mapToResponse(savedOrder);
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException(
                                "Order not found"));

        return mapToResponse(order);
    }

    @Override
    public List<OrderResponse> getOrderByUser(Long userId) {
        
        return orderRepository.findByUserId(userId)
                            .stream()
                            .map(this::mapToResponse)
                            .toList();
    }

    @Override
    public OrderResponse updateOrderStatus(Long orderId, OrderStatus status) {

        Order order = orderRepository.findById(orderId)
                            .orElseThrow(() -> new ResourceNotFoundException(
                                "Order not found"));

        order.setStatus(status);

        Order updatedOrder = orderRepository.save(order);

        return mapToResponse(updatedOrder);
    }

    private OrderResponse mapToResponse(Order order) {

        List<OrderItemResponse> items = orderItemRepository
                                            .findByOrderId(order.getId())
                                        .stream()
                                        .map(item -> 
                                            OrderItemResponse.builder()
                                                .menuItemId(item.getMenuItem().getId())
                                                .menuItemName(item.getMenuItem().getName())
                                                .quantity(item.getQuantity())
                                                .priceSnapshot(item.getPriceSnapshot())
                                                .build()
                                        )
                                        .toList();

        return OrderResponse.builder()
                    .orderId(order.getId())
                    .userId(order.getUser().getId())
                    .customerName(order.getUser().getName())
                    .restaurantId(order.getRestaurant().getId())
                    .restaurantName(order.getRestaurant().getName())
                    .status(order.getStatus())
                    .totalPrice(order.getTotalPrice())
                    .paymentStatus(order.getPaymentStatus())
                    .items(items)
                    .build();
    }
}
