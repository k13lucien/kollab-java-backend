package com.kollab.team;

import com.kollab.authentication.model.User;
import com.kollab.authentication.repository.UserRepository;
import com.kollab.team.dto.TeamRequestDTO;
import com.kollab.team.dto.TeamResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamService {

    private final TeamMapper mapper;
    private final TeamRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public TeamService(TeamRepository repository, TeamMapper mapper, UserRepository userRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    @Transactional
    public TeamResponseDTO saveTeam(TeamRequestDTO dto) {
        User currentUser = getCurrentUser();
        Team team = mapper.toTeam(dto);
        team.setOwner(currentUser);
        team.getMembers().add(currentUser);
        
        Team savedTeam = repository.save(team);
        return mapper.toTeamResponseDTO(savedTeam);
    }

//    public TeamRequestDTO retrieveTeam(Integer id) {
//        var team = repository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Team not found"));
//        return mapper.toTeamDTO(team);
//    }

//    public List<TeamRequestDTO> retrieveAllTeams() {
//        return repository.findAll()
//                .stream()
//                .map(mapper::toTeamDTO)
//                .toList();
//    }

//    @Transactional
//    public TeamRequestDTO updateTeam(TeamRequestDTO dto, Integer id) {
//        Team existingTeam = repository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Team not found"));
//
//        // Optionnel: vérifier que c'est le propriétaire qui modifie
//        // checkOwner(existingTeam);
//
//        existingTeam.setName(dto.name());
//        existingTeam.setLabel(dto.label());
//        Team updatedTeam = repository.save(existingTeam);
//        return mapper.toTeamDTO(updatedTeam);
//    }

//    @Transactional
//    public void deleteTeam(Integer id) {
//        Team existingTeam = repository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Team not found"));
//
//        // Optionnel: vérifier que c'est le propriétaire qui supprime
//        checkOwner(existingTeam);
//
//        repository.delete(existingTeam);
//    }

//    @Transactional
//    public void addMember(Integer teamId, Integer userId) {
//        Team team = repository.findById(teamId)
//                .orElseThrow(() -> new EntityNotFoundException("Team not found"));
//
//        // Optionnel: seul le propriétaire peut ajouter des membres
//        checkOwner(team);
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new EntityNotFoundException("User not found"));
//
//        team.getMembers().add(user);
//        repository.save(team);
//    }

//    @Transactional
//    public void removeMember(Integer teamId, Integer userId) {
//        Team team = repository.findById(teamId)
//                .orElseThrow(() -> new EntityNotFoundException("Team not found"));
//
//        checkOwner(team);
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new EntityNotFoundException("User not found"));
//
//        if (team.getOwner().equals(user)) {
//            throw new IllegalStateException("Cannot remove the owner from the team");
//        }
//
//        team.getMembers().remove(user);
//        repository.save(team);
//    }

    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username);
    }

//    private void checkOwner(Team team) {
//        User currentUser = getCurrentUser();
//        if (!team.getOwner().equals(currentUser)) {
//            throw new IllegalStateException("Only the owner can perform this action");
//        }
//    }
}
