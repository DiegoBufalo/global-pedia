package db.dbspringtemplate.service.impl;

import db.dbspringtemplate.dto.TeamDto;
import db.dbspringtemplate.error.RestException;
import db.dbspringtemplate.model.Team;
import db.dbspringtemplate.repository.TeamRepository;
import db.dbspringtemplate.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    public static final String TEAM_NOT_FOUND = "Team not found";

    private final TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl (TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeamDto> getAllTeams(boolean active) {
        if (active)
            return TeamDto.fromEntity(teamRepository.findAllActive());

        return TeamDto.fromEntity(teamRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeamDto> getAllTeamsByName(String name) {
        return TeamDto.fromEntity(teamRepository.findAllByName(name));
    }

    @Override
    @Transactional(readOnly = true)
    public TeamDto getTeam(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> {
            throw new RestException(HttpStatus.NOT_FOUND, TEAM_NOT_FOUND);
        });
        return TeamDto.fromEntity(team);
    }

    @Override
    @Transactional
    public TeamDto createTeam(TeamDto teamDto) {

        List<Team> teams = teamRepository.findAllByName(teamDto.getName());
        if (!teams.isEmpty())
            throw new RestException(HttpStatus.CONFLICT, "Time já cadastrado com este nome");

        Team team = Team.builder()
                .name(teamDto.getName())
                .creation(teamDto.getCreation())
                .active(true)
                .build();

        return TeamDto.fromEntity(teamRepository.save(team));
    }

    @Override
    @Transactional
    public TeamDto updateTeam(Long id, TeamDto teamDto) {
        Team team = teamRepository.findById(id).orElseThrow(() -> {
            throw new RestException(HttpStatus.NOT_FOUND, TEAM_NOT_FOUND);
        });

        if (!team.getName().equals(teamDto.getName())) {
            List<Team> teams = teamRepository.findAllByName(teamDto.getName());
            if (!teams.isEmpty())
                throw new RestException(HttpStatus.CONFLICT, "Nome de time já cadastrado");
        }

        team.setName(teamDto.getName());
        team.setCreation(teamDto.getCreation());

        return TeamDto.fromEntity(team);
    }

    @Override
    @Transactional
    public void inactiveTeam(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> {
            throw new RestException(HttpStatus.NOT_FOUND, TEAM_NOT_FOUND);
        });

        team.setActive(false);
    }

    @Override
    @Transactional
    public void reactiveTeam(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> {
            throw new RestException(HttpStatus.NOT_FOUND, TEAM_NOT_FOUND);
        });

        team.setActive(true);
    }
}
