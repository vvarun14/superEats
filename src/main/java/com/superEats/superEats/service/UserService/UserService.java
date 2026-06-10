package com.superEats.superEats.service.UserService;

import com.superEats.superEats.dto.UserResponse;

import java.util.List;

import com.superEats.superEats.dto.CreateUserRequest;

public interface UserService {
    
    UserResponse createUser(CreateUserRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    UserResponse updateUser(Long id, CreateUserRequest request);

    void deleteUser(Long id);

}
