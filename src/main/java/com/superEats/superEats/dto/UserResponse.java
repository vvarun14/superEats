package com.superEats.superEats.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    
    public Long id;
    public String name;
    public String email;
}
