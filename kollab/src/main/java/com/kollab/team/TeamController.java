package com.kollab.team;

import com.kollab.team.dto.TeamRequestDTO;
import com.kollab.team.dto.TeamResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/teams")
public class TeamController {

    private final TeamService service;

    @Autowired
    public TeamController(TeamService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamResponseDTO saveTeam(
            @RequestBody TeamRequestDTO dto
    ) {
        return service.saveTeam(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TeamResponseDTO> getAllTeams() {
        return service.retrieveAllTeams();
    }

    @GetMapping("/{team-id}")
    @ResponseStatus(HttpStatus.OK)
    public TeamResponseDTO getTeamById(
            @PathVariable("team-id") Integer id
    ) {
        return service.retrieveTeam(id);
    }

    @PutMapping("/{team-id}")
    @ResponseStatus(HttpStatus.OK)
    public TeamResponseDTO updateTeam(
            @PathVariable("team-id") Integer id,
            @RequestBody TeamRequestDTO dto
    ) {
        return service.updateTeam(dto, id);
    }

    @DeleteMapping("/{team-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteTeam(
            @PathVariable("team-id") Integer id
    ) {
        service.deleteTeam(id);
    }

    @PostMapping("/{team-id}/members/{user-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addMember(
            @PathVariable("team-id") Integer teamId,
            @PathVariable("user-id") UUID userId
    ) {
        service.addMember(teamId, userId);
    }

    @DeleteMapping("/{team-id}/members/{user-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeMember(
            @PathVariable("team-id") Integer teamId,
            @PathVariable("user-id") UUID userId
    ) {
        service.removeMember(teamId, userId);
    }
}
