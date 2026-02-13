package com.kollab.team.dto;

import com.kollab.authentication.dto.UserResponseDTO;

import java.util.Set;

public record TeamResponseDTO(
        String name,
        String label,
        UserResponseDTO owner,
        Set<UserResponseDTO> members
) {
}
