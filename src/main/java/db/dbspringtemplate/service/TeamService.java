package db.dbspringtemplate.service;

import db.dbspringtemplate.dto.TeamDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeamService {

    List<TeamDto> getAllTeams(boolean active);

    List<TeamDto> getAllTeamsByName(String name);

    TeamDto getTeam(Long id);

    TeamDto createTeam(TeamDto teamDto);

    TeamDto updateTeam(Long id, TeamDto teamDto);

    void inactiveTeam(Long id);

    void reactiveTeam(Long id);
}
