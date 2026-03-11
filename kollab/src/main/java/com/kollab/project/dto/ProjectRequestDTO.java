package com.kollab.project.dto;

import java.time.LocalDateTime;

public record ProjectRequestDTO(
        String name,
        String label,
        LocalDateTime startDate,
        LocalDateTime deadline,
        Integer teamId
) {
}
