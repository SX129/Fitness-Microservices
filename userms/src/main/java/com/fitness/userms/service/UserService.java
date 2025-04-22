package com.fitness.userms.service;

import com.fitness.userms.dto.RegisterRequest;
import com.fitness.userms.dto.UserDTO;
import com.fitness.userms.model.User;

import java.util.List;

public interface UserService {
    public UserDTO getUserById(Long userId);

    public List<UserDTO> getAllUsers();

    public List<UserDTO> getAllUsersByRole(String role);

    public UserDTO createUser(RegisterRequest request);

    public UserDTO updateUserById(Long userId, User user);

    public boolean deleteUserById(Long userId);

    Boolean existsByUserId(Long userId);
}
