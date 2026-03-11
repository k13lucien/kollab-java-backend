package com.kollab.authentication.dto;

public record UserRequestDTO(
        String firstname,
        String lastname,
        String email,
        String username,
        String password
) {
}
