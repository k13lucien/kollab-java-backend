package com.kollab.project;

import com.kollab.project.dto.ProjectRequestDTO;
import com.kollab.project.dto.ProjectResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
    
    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponseDTO saveProject(
            @RequestBody ProjectRequestDTO dto
    ) {
        return service.saveProject(dto);
    }

    @GetMapping("/teams/{team-id}")
    public List<ProjectResponseDTO> getProjects(
            @PathVariable("team-id") Integer teamId
    ) {
        return service.retrieveProjects(teamId);
    }

    @GetMapping("{project-id}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectResponseDTO getProjectById(
            @PathVariable("project-id") Integer id
    ) {
        return service.retrieveProject(id);
    }

    @PutMapping("{project-id}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectResponseDTO updateProject(
            @PathVariable("project-id") Integer id,
            @RequestBody ProjectRequestDTO dto
    ) {
        return service.updateProject(dto, id);
    }

    @DeleteMapping("{project-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteProject(
            @PathVariable("project-id") Integer id
    ) {
        service.deleteProject(id);
    }
}
