package com.fitness.userms.mapper;

import com.fitness.userms.dto.UserDTO;
import com.fitness.userms.model.User;

public class UserMapper {
    public static UserDTO mapToUserDTO(User user){
        UserDTO userDTO = new UserDTO();

        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());

        return userDTO;
    }
}
