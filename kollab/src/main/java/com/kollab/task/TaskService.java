package com.kollab.task;

import com.kollab.authentication.model.User;
import com.kollab.authentication.repository.UserRepository;
import com.kollab.project.Project;
import com.kollab.project.ProjectRepository;
import com.kollab.task.dto.TaskRequestDTO;
import com.kollab.task.dto.TaskResponseDTO;
import com.kollab.team.Team;
import com.kollab.team.TeamRepository;
import com.kollab.team.TeamService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    private final TaskMapper mapper;
    private final TaskRepository repository;
    private final TeamService teamService;
    private final ProjectRepository projectRepository;
    private final TeamRepository teamRepository;


    public TaskService(TaskMapper taskMapper, TaskRepository taskRepository, TeamService teamService, TeamRepository teamRepository, ProjectRepository projectRepository, UserRepository userRepository) {
        this.mapper = taskMapper;
        this.repository = taskRepository;
        this.teamService = teamService;
        this.projectRepository = projectRepository;
        this.teamRepository = teamRepository;
    }

    public TaskResponseDTO retrieveTask(Integer id) {
        Task task = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Task not found")
        );
        teamService.checkMembership(task.getProject().getTeam());
        return mapper.toTaskResponseDTO(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found")));
    }

    @Transactional
    public TaskResponseDTO saveTask(TaskRequestDTO dto) {
        Project project = projectRepository.findById(dto.projectId()).orElseThrow(
                () -> new EntityNotFoundException("Project not found")
        );
        teamService.checkOwner(project.getTeam());
        Task savedTask = repository.save(mapper.toTask(dto));
        return mapper.toTaskResponseDTO(savedTask);
    }

    @Transactional
    public TaskResponseDTO updateTask(TaskRequestDTO dto, Integer id) {
        Task existingTask = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));

        Project project = projectRepository.findById(existingTask.getProject().getId()).orElseThrow(
                () -> new EntityNotFoundException("Project not found")
        );
        teamService.checkOwner(project.getTeam());

        existingTask.setTitle(dto.title());
        existingTask.setLabel(dto.label());
        existingTask.setStatus(dto.status());
        existingTask.setPriority(dto.priority());
        existingTask.setDeadline(dto.deadline());

        return mapper.toTaskResponseDTO(repository.save(existingTask));
    }

    @Transactional
    public void deleteTask(Integer id) {
        Task existingTask = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));

        Project project = projectRepository.findById(existingTask.getProject().getId()).orElseThrow(
                () -> new EntityNotFoundException("Project not found")
        );
        teamService.checkOwner(project.getTeam());

        repository.delete(existingTask);
    }

    public List<TaskResponseDTO> retrieveTasks(Integer projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new EntityNotFoundException("Project not found")
        );
        teamService.checkMembership(project.getTeam());
        return repository.findByProjectId(projectId)
                .stream()
                .map(mapper::toTaskResponseDTO)
                .toList();
    }
}
