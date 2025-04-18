package com.fitness.userms.service;

import com.fitness.userms.model.User;

import java.util.List;

public interface UserService {
    public User getUserById(Long userId);

    public List<User> getAllUsers();

    public List<User> getAllUsersByRole(String role);

    public User createUser(User user);

    public User updateUserById(Long userId, User user);

    public boolean deleteUserById(Long userId);
}
