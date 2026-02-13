package com.kollab.team.dto;

import com.kollab.authentication.model.User;

import java.util.Set;
import java.util.UUID;

public record TeamResponseDTO(
        String name,
        String label,
        User owner,
        Set<User> members
) {
}
