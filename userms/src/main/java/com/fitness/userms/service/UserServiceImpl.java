package com.fitness.userms.service;

import com.fitness.userms.dto.RegisterRequest;
import com.fitness.userms.dto.UserDTO;
import com.fitness.userms.mapper.UserMapper;
import com.fitness.userms.model.User;
import com.fitness.userms.model.UserRole;
import com.fitness.userms.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return UserMapper.mapToUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> UserMapper.mapToUserDTO(user)).toList();
    }

    @Override
    public List<UserDTO> getAllUsersByRole(String role) {
        if(role.equals("USER")){
            List<User> users = userRepository.findAll().stream().filter(user -> user.getRole() == UserRole.USER).toList();
            return users.stream().map(user -> UserMapper.mapToUserDTO(user)).collect(Collectors.toList());
        }else if(role.equals("ADMIN")){
            List<User> users = userRepository.findAll().stream().filter(user -> user.getRole() == UserRole.ADMIN).toList();
            return users.stream().map(user -> UserMapper.mapToUserDTO(user)).collect(Collectors.toList());
        }

        return null;
    }

    @Override
    public UserDTO createUser(RegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exists.");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        userRepository.save(user);
        return UserMapper.mapToUserDTO(user);
    }

    @Override
    public UserDTO updateUserById(Long userId, User user) {
        User updatedUser = userRepository.findById(userId).orElse(null);

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

        return UserMapper.mapToUserDTO(updatedUser);
    }

    @Override
    public boolean deleteUserById(Long userId) {
        User user =  userRepository.findById(userId).orElse(null);

        if(user == null){
            return false;
        }

        userRepository.deleteById(userId);

        return true;
    }

    @Override
    public Boolean existsByUserId(Long userId) {
        return userRepository.existsById(userId);
    }
}
