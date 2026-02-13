package com.kollab.team;

import com.kollab.authentication.dto.UserResponseDTO;
import com.kollab.authentication.mapper.UserMapper;
import com.kollab.team.dto.TeamRequestDTO;
import com.kollab.team.dto.TeamResponseDTO;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TeamMapper {

    private final UserMapper userMapper;

    public TeamMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Team toTeam(TeamRequestDTO dto) {
        var team = new Team();
        team.setName(dto.name());
        team.setLabel(dto.label());

        return team;
    }

    public TeamResponseDTO toTeamResponseDTO(Team team) {

        UserResponseDTO owner = userMapper.toUserResponseDTO(team.getOwner());
        Set<UserResponseDTO> members = team.getMembers()
                                        .stream()
                                        .map(userMapper::toUserResponseDTO)
                                        .collect(Collectors.toSet());

        return new TeamResponseDTO(
                team.getName(),
                team.getLabel(),
                owner,
                members
        );
    }
}
