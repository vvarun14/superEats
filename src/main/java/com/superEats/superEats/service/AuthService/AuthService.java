package com.superEats.superEats.service.AuthService;

import com.superEats.superEats.dto.AuthResponse;
import com.superEats.superEats.dto.LoginRequest;
import com.superEats.superEats.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
