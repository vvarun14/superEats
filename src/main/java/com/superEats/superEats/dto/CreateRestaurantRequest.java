package com.superEats.superEats.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateRestaurantRequest {
    
    @NotBlank
    private String name;

    @NotBlank
    private String address;

    private Double latitude;

    private Double longitude;
}
