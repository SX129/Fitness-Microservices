package com.fitness.userms.service;

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
        User user = userRepository.getReferenceById(userId);
        return UserMapper.mapToUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> UserMapper.mapToUserDTO(user)).toList();
    }

    @Override
    public List<UserDTO> getAllUsersByRole(String role) {
        if(role == "USER"){
            List<User> users = userRepository.findAll().stream().filter(user -> user.getRole() == UserRole.USER).toList();
            return users.stream().map(user -> UserMapper.mapToUserDTO(user)).collect(Collectors.toList());
        }else if(role == "ADMIN"){
            List<User> users = userRepository.findAll().stream().filter(user -> user.getRole() == UserRole.ADMIN).toList();
            return users.stream().map(user -> UserMapper.mapToUserDTO(user)).collect(Collectors.toList());
        }

        return null;
    }

    @Override
    public UserDTO createUser(User user) {
        userRepository.save(user);
        return UserMapper.mapToUserDTO(user);
    }

    @Override
    public UserDTO updateUserById(Long userId, User user) {
        User updatedUser = userRepository.getReferenceById(userId);

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

        return UserMapper.mapToUserDTO(user);
    }

    @Override
    public boolean deleteUserById(Long userId) {
        User user =  userRepository.getReferenceById(userId);

        if(user == null){
            return false;
        }

        userRepository.deleteById(userId);

        return true;
    }
}
