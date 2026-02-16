package com.kollab.project;

import com.kollab.project.dto.ProjectRequestDTO;
import com.kollab.project.dto.ProjectResponseDTO;
import com.kollab.team.Team;
import com.kollab.team.TeamRepository;
import com.kollab.team.TeamService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectService {

    public final ProjectMapper mapper;
    public final ProjectRepository repository;
    private final TeamService teamService;
    private final TeamRepository teamRepository;

    public ProjectService(ProjectMapper mapper, ProjectRepository repository, TeamService teamService, TeamRepository teamRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.teamService = teamService;
        this.teamRepository = teamRepository;
    }

    @Transactional
    public ProjectResponseDTO saveProject(ProjectRequestDTO dto) {
        Project project = mapper.toProject(dto);
        Project savedProject = repository.save(project);

        teamService.checkOwner(teamRepository.findById(dto.teamId()).orElseThrow(
                () -> new EntityNotFoundException("Team not found")
        ));

        return mapper.toProjectResponseDTO(savedProject);
    }

    public ProjectResponseDTO retrieveProject(Integer id) {
        Project project = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));

        return mapper.toProjectResponseDTO(project);
    }

    public List<ProjectResponseDTO> retrieveAllProject() {
        return repository.findAll()
                .stream()
                .map(mapper::toProjectResponseDTO)
                .toList();
    }

    @Transactional
    public ProjectResponseDTO updateProject(ProjectRequestDTO dto, Integer id) {
        Project existingProject = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));

        teamService.checkOwner(teamRepository.findById(existingProject.getTeam().getId()).orElseThrow(
                () -> new EntityNotFoundException("Team not found")
        ));

        existingProject.setName(dto.name());
        existingProject.setLabel(dto.label());
        existingProject.setStartDate(dto.startDate());
        existingProject.setDeadline(dto.deadline());

        Project updatedProject = repository.save(existingProject);

        return mapper.toProjectResponseDTO(updatedProject);
    }

    @Transactional
    public void deleteProject(Integer id) {
        Project existingProject = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));

        teamService.checkOwner(teamRepository.findById(existingProject.getTeam().getId()).orElseThrow(
                () -> new EntityNotFoundException("Team not found")
        ));

        repository.delete(existingProject);
    }
}
