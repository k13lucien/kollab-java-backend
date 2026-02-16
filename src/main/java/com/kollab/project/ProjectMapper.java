package com.kollab.project;

import com.kollab.project.dto.ProjectRequestDTO;
import com.kollab.project.dto.ProjectResponseDTO;
import com.kollab.team.Team;
import com.kollab.team.TeamRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectMapper {

    private final TeamRepository teamRepository;

    public ProjectMapper(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Project toProject(ProjectRequestDTO dto) {
        Project project = new Project();
        project.setName(dto.name());
        project.setLabel(dto.label());
        project.setStartDate(dto.startDate());
        project.setDeadline(dto.deadline());
        project.setTeam(teamRepository.findById(dto.teamId()).orElse(new Team()));

        return project;
    }

    public ProjectResponseDTO toProjectResponseDTO(Project project) {
        return new ProjectResponseDTO(
                project.getId(),
                project.getName(),
                project.getLabel(),
                project.getStartDate(),
                project.getDeadline(),
                project.getTeam().getId()
        );
    }
}
