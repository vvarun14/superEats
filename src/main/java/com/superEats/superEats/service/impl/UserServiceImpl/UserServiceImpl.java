package com.superEats.superEats.service.impl.UserServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.superEats.superEats.dto.CreateUserRequest;
import com.superEats.superEats.dto.UserResponse;
import com.superEats.superEats.exception.ResourceNotFoundException;
import com.superEats.superEats.model.User;
import com.superEats.superEats.repository.UserRepository;
import com.superEats.superEats.service.UserService.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(
                    CreateUserRequest request) {
        
        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        
        // Temporary
        user.setPassword(request.getPassword());

        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }

    @Override
    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                            .stream()
                            .map(this::mapToResponse)
                            .toList();
    }

    @Override
    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return mapToResponse(user);
    }

    @Override
    public UserResponse updateUser(Long id, CreateUserRequest request) {

        User user = userRepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        User updatedUser = userRepository.save(user);

        return mapToResponse(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        userRepository.delete(user);
    }

    public UserResponse mapToResponse(User user) {

        return UserResponse.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .build();
    }
}
