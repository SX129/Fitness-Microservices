package com.fitness.userms.controller;

import com.fitness.userms.dto.UserDTO;
import com.fitness.userms.model.User;
import com.fitness.userms.responses.UserResponse;
import com.fitness.userms.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/v1/users")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId){
        UserDTO userDTO = userService.getUserById(userId);

        if(userDTO == null){
            return new ResponseEntity<>(new UserResponse(null, "User not found."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new UserResponse(userDTO, "User found."), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<UserResponse> getAllUsers(@RequestParam(required = false) String role){
        List<UserDTO> userDTOs;

        if(role == null){
            userDTOs = userService.getAllUsers();

            if(userDTOs.size() == 0){
                return new ResponseEntity<>(new UserResponse(userDTOs, "Users not found."), HttpStatus.NOT_FOUND);
            }

        }else{
            userDTOs = userService.getAllUsersByRole(role);

            if(userDTOs == null){
                return new ResponseEntity<>(new UserResponse<>(null, "Invalid role."), HttpStatus.NOT_FOUND);
            }else if(userDTOs.size() == 0){
                return new ResponseEntity<>(new UserResponse(userDTOs, "Users not found."), HttpStatus.NOT_FOUND);
            }

        }
        return new ResponseEntity<>(new UserResponse(userDTOs, "Users found."), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UserResponse> createUser(@RequestBody User user){
        UserDTO userDTO = userService.createUser(user);
        return new ResponseEntity<>(new UserResponse(userDTO, "User created."), HttpStatus.CREATED);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUserById(@PathVariable Long userId, @RequestBody User user){
        UserDTO userDTO = userService.updateUserById(userId, user);

        if(userDTO == null){
            return new ResponseEntity<>(new UserResponse(null, "User not found."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new UserResponse(userDTO, "User updated."), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserResponse> deleteUserById(@PathVariable Long userId){
        boolean userDeleted = userService.deleteUserById(userId);

        if(!userDeleted){
            return new ResponseEntity<>(new UserResponse(null, "User not found."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new UserResponse(null, "User deleted."), HttpStatus.OK);
    }
}
