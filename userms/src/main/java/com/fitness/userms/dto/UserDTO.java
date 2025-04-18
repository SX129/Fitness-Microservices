package com.fitness.userms.dto;

public class UserDTO {
    private String email;
    private String firstName;
    private String lastName;

    public UserDTO(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
