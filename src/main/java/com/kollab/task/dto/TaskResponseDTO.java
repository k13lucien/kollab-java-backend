package com.kollab.task.dto;

import com.kollab.task.enums.TaskPriority;
import com.kollab.task.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskResponseDTO(
        Integer id,
        String title,
        String label,
        TaskStatus status,
        TaskPriority priority,
        LocalDateTime deadline,
        Integer projectId,
        UUID userId
) {
}
