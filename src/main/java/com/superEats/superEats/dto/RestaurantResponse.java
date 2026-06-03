package com.superEats.superEats.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantResponse {
    private Long id;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private boolean isOpen;
}
