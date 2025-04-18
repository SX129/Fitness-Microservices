package com.fitness.userms.controller;

import com.fitness.userms.model.User;
import com.fitness.userms.service.UserServiceImpl;
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
    public ResponseEntity<User> getUserById(@PathVariable Long userId){

    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String role){

    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user){

    }

    @PatchMapping("/{userId}")
    public ResponseEntity<User> updateUserById(@PathVariable Long userId, @RequestBody User user){

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long userId){

    }
}
