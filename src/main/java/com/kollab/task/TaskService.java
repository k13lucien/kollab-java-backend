package com.kollab.task;

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


    public TaskService(TaskMapper taskMapper, TaskRepository taskRepository, TeamService teamService, TeamRepository teamRepository, ProjectRepository projectRepository) {
        this.mapper = taskMapper;
        this.repository = taskRepository;
        this.teamService = teamService;
        this.teamRepository = teamRepository;
        this.projectRepository = projectRepository;
    }
//
//    public List<TaskRequestDTO> retrieveAllTask() {
//        return repository.findAll()
//                .stream()
//                .map(mapper::toTaskDTO)
//                .toList();
//    }
//
//    public TaskRequestDTO retrieveTask(Integer id) {
//        return mapper.toTaskDTO(repository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Task not found")));
//    }

    @Transactional
    public TaskResponseDTO saveTask(TaskRequestDTO dto) {
        Project project = projectRepository.findById(dto.projectId()).orElseThrow(
                () -> new EntityNotFoundException("Project not found")
        );
        teamService.checkOwner(project.getTeam());
        Task savedTask = repository.save(mapper.toTask(dto));
        return mapper.toTaskResponseDTO(savedTask);
    }
//
//    public TaskRequestDTO updateTask(TaskRequestDTO dto, Integer id) {
//        Task existingTask = repository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
//
//        Task updatedTask = new Task();
//        updatedTask.setTitle(dto.title());
//        updatedTask.setLabel(dto.label());
//        updatedTask.setPriority(dto.priority());
//        updatedTask.setStatus(dto.status());
//        updatedTask.setDeadline(dto.deadline());
//
//        repository.save(updatedTask);
//
//        return mapper.toTaskDTO(updatedTask);
//    }
//
//    public void deleteTask(Integer id) {
//        Task existingTask = repository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
//
//        repository.delete(existingTask);
//    }
}
