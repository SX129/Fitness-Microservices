package com.fitness.userms.service;

import com.fitness.userms.model.User;
import com.fitness.userms.model.UserRole;
import com.fitness.userms.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.getReferenceById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllUsersByRole(String role) {
        if(role == "USER"){
            return userRepository.findAll().stream().filter(user -> user.getRole() == UserRole.USER).collect(Collectors.toList());
        }else if(role == "ADMIN"){
            return userRepository.findAll().stream().filter(user -> user.getRole() == UserRole.ADMIN).collect(Collectors.toList());
        }

        return null;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUserById(Long userId, User user) {
        User updatedUser = getUserById(userId);

        if(updatedUser != null){
            if(user.getEmail() != null){
                updatedUser.setEmail(user.getEmail());
            }

            if(user.getFirstName() != null){
                updatedUser.setFirstName(user.getFirstName());
            }

            if(user.getLastName() != null){
                updatedUser.setLastName(user.getLastName());
            }

            if(user.getPassword() != null){
                updatedUser.setPassword(user.getPassword());
            }

            updatedUser.setUpdatedAt(LocalDateTime.now());
            userRepository.save(updatedUser);
        }

        return updatedUser;
    }

    @Override
    public boolean deleteUserById(Long userId) {
        User user = getUserById(userId);

        if(user == null){
            return false;
        }

        userRepository.deleteById(userId);

        return true;
    }
}
