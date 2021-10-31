package db.dbspringtemplate.service.impl;

import db.dbspringtemplate.dto.TeamPlayedDto;
import db.dbspringtemplate.error.RestException;
import db.dbspringtemplate.model.Player;
import db.dbspringtemplate.model.Team;
import db.dbspringtemplate.model.TeamPlayed;
import db.dbspringtemplate.repository.PlayerRepository;
import db.dbspringtemplate.repository.TeamPlayedRepository;
import db.dbspringtemplate.repository.TeamRepository;
import db.dbspringtemplate.service.TeamPlayedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamPlayedServiceImpl implements TeamPlayedService {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final TeamPlayedRepository teamPlayedRepository;

    @Autowired
    public TeamPlayedServiceImpl (TeamRepository teamRepository, PlayerRepository playerRepository,
                                  TeamPlayedRepository teamPlayedRepository){
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
        this.teamPlayedRepository = teamPlayedRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeamPlayedDto> getAllTeamsPlayedByPlayer(Long id) {
        Player player = playerRepository.findById(id).orElseThrow(() -> {
            throw new RestException(HttpStatus.NOT_FOUND, "Jogador não encontrado");
        });

        return TeamPlayedDto.fromEntity(teamPlayedRepository.findAllByPlayer(player.getId()));
    }

    @Override
    public List<TeamPlayedDto> getAllTeamsPlayedByTeam(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(()-> {
           throw new RestException(HttpStatus.NOT_FOUND, "Time não encontrado");
        });
        return TeamPlayedDto.fromEntity(teamPlayedRepository.findAllByTeam(team.getId()));
    }

    @Override
    @Transactional
    public TeamPlayedDto createTeamPlayed(TeamPlayedDto teamPlayedDto) {
        Player player = playerRepository.findById(teamPlayedDto.getPlayer().getId()).orElseThrow(() -> {
            throw new RestException(HttpStatus.NOT_FOUND, "Jogador não encontrado");
        });
        Team team = teamRepository.findById(teamPlayedDto.getTeam().getId()).orElseThrow(()-> {
            throw new RestException(HttpStatus.NOT_FOUND, "Time não encontrado");
        });

        TeamPlayed teamPlayed = TeamPlayed.builder()
                .team(team)
                .player(player)
                .startDate(teamPlayedDto.getStartDate())
                .endDate(teamPlayedDto.getEndDate())
                .active(teamPlayedDto.isActive())
                .build();

        return TeamPlayedDto.fromEntity(teamPlayedRepository.save(teamPlayed));
    }

    @Override
    @Transactional
    public TeamPlayedDto updateTeamPlayed(Long id, TeamPlayedDto teamPlayedDto) {
        TeamPlayed teamPlayed = teamPlayedRepository.findById(id).orElseThrow(() -> {
            throw new RestException(HttpStatus.NOT_FOUND, "Time jogador não encontrado");
        });

        teamPlayed.setTeam(teamPlayedDto.getTeam());
        teamPlayed.setPlayer(teamPlayedDto.getPlayer());
        teamPlayed.setStartDate(teamPlayedDto.getStartDate());
        teamPlayed.setEndDate(teamPlayedDto.getEndDate());
        teamPlayed.setActive(teamPlayedDto.isActive());

        return TeamPlayedDto.fromEntity(teamPlayed);
    }

    @Override
    @Transactional
    public void deleteTeamPlayedDto(Long id) {
        TeamPlayed teamPlayed = teamPlayedRepository.findById(id).orElseThrow(() -> {
            throw new RestException(HttpStatus.NOT_FOUND, "Time jogador não encontrado");
        });
        teamPlayedRepository.delete(teamPlayed);
    }
}
