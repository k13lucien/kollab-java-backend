package com.kollab.task;

import com.kollab.authentication.model.User;
import com.kollab.authentication.repository.UserRepository;
import com.kollab.project.Project;
import com.kollab.project.ProjectRepository;
import com.kollab.task.dto.TaskRequestDTO;
import com.kollab.task.dto.TaskResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class TaskMapper {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public TaskMapper(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public Task toTask(TaskRequestDTO dto) {
        Task task  = new Task();
        task.setTitle(dto.title());
        task.setLabel(dto.label());
        task.setStatus(dto.status());
        task.setPriority(dto.priority());
        task.setDeadline(dto.deadline());

        Project project = projectRepository.findById(dto.projectId()).orElseThrow(
                () -> new IllegalArgumentException("Project not found")
        );
        User user = userRepository.findById(dto.userId()).orElseThrow(
                () -> new IllegalArgumentException("User not found")
        );

        task.setProject(project);
        task.setUser(user);

        return task;
    }

    public TaskResponseDTO toTaskResponseDTO(Task task) {
        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getLabel(),
                task.getStatus(),
                task.getPriority(),
                task.getDeadline(),
                task.getProject().getId(),
                task.getUser().getId()
        );
    }
}
