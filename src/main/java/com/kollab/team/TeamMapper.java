package com.kollab.team;

import com.kollab.team.dto.TeamRequestDTO;
import com.kollab.team.dto.TeamResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class TeamMapper {

    public Team toTeam(TeamRequestDTO dto) {
        var team = new Team();
        team.setName(dto.name());
        team.setLabel(dto.label());

        return team;
    }

    public TeamResponseDTO toTeamResponseDTO(Team team) {

        return new TeamResponseDTO(
                team.getName(),
                team.getLabel(),
                team.getOwner(),
                team.getMembers()
        );
    }
}
