package com.superEats.superEats.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateMenuItemRequest {
    
    @NotBlank
    private String name;

    @NotNull
    @DecimalMin("0.0")
    private Double price;

    @NotNull
    private Long restaurantId;
}
