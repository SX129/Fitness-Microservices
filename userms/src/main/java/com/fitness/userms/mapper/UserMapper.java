package com.fitness.userms.mapper;

import com.fitness.userms.dto.UserDTO;
import com.fitness.userms.model.User;

public class UserMapper {
    public static UserDTO mapToUserDTO(User user){
        return new UserDTO(user.getEmail(), user.getFirstName(), user.getLastName());
    }
}
