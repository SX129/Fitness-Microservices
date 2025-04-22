package com.fitness.userms.controller;

import com.fitness.userms.dto.RegisterRequest;
import com.fitness.userms.dto.UserDTO;
import com.fitness.userms.model.User;
import com.fitness.userms.responses.UserResponse;
import com.fitness.userms.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId){
        try{
            UserDTO userDTO = userService.getUserById(userId);
            return new ResponseEntity<>(new UserResponse(userDTO, "User found."), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new UserResponse(null, "User not found."), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity<UserResponse> getAllUsers(@RequestParam(required = false) String role){
        List<UserDTO> userDTOs;

        if(role == null){
            userDTOs = userService.getAllUsers();
        }else{
            userDTOs = userService.getAllUsersByRole(role);

            if(userDTOs == null){
                return new ResponseEntity<>(new UserResponse<>(null, "Invalid role."), HttpStatus.NOT_FOUND);
            }
        }

        if(userDTOs.size() == 0){
            return new ResponseEntity<>(new UserResponse(userDTOs, "Users not found."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new UserResponse(userDTOs, "Users found."), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody RegisterRequest request){
        try {
            UserDTO userDTO = userService.createUser(request);
            return new ResponseEntity<>(new UserResponse(userDTO, "User created."), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(new UserResponse(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUserById(@PathVariable Long userId, @RequestBody User user){
        try {
            UserDTO userDTO = userService.updateUserById(userId, user);
            return new ResponseEntity<>(new UserResponse(userDTO, "User updated."), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new UserResponse(null, "User not found."), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserResponse> deleteUserById(@PathVariable Long userId){
        boolean userDeleted = userService.deleteUserById(userId);

        if(!userDeleted){
            return new ResponseEntity<>(new UserResponse(null, "User not found."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new UserResponse(null, "User deleted."), HttpStatus.OK);
    }

    @GetMapping("/{userId}/validate")
    public ResponseEntity<Boolean> validateUser(@PathVariable Long userId){
        return new ResponseEntity<>(userService.existsByUserId(userId), HttpStatus.OK);
    }
}
