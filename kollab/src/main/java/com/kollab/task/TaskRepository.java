package com.kollab.task;

import com.kollab.task.dto.TaskResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByProjectId(Integer projectId);
}
