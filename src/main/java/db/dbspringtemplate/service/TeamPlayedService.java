package db.dbspringtemplate.service;

import db.dbspringtemplate.dto.TeamPlayedDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeamPlayedService {

    List<TeamPlayedDto> getAllTeamsPlayedByPlayer(Long id);

    List<TeamPlayedDto> getAllTeamsPlayedByTeam(Long id);

    TeamPlayedDto createTeamPlayed(TeamPlayedDto teamPlayedDto);

    TeamPlayedDto updateTeamPlayed(Long id, TeamPlayedDto teamPlayedDto);

    void deleteTeamPlayedDto(Long id);
}
