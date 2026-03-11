package com.kollab.authentication.dto;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String firstname,
        String lastname,
        String email,
        String username
) {
}
