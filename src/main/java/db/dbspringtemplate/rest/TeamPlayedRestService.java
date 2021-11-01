package db.dbspringtemplate.rest;

import db.dbspringtemplate.dto.TeamPlayedDto;
import db.dbspringtemplate.service.TeamPlayedService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/team_played")
public class TeamPlayedRestService extends AllowCorsService{

    private final TeamPlayedService teamPlayedService;

    @Autowired
    public TeamPlayedRestService(TeamPlayedService teamPlayedService) {
        this.teamPlayedService = teamPlayedService;
    }

    @GetMapping("/player/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Busca todos times de um jogador")
    public List<TeamPlayedDto> getAllTeamsPlayedByPlayer(@PathVariable Long id) {
        return teamPlayedService.getAllTeamsPlayedByPlayer(id);
    }

    @GetMapping("/team/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Busca todos os jogadores de um time")
    public List<TeamPlayedDto> getAllTeamsPlayedByTeam(@PathVariable Long id) {
        return teamPlayedService.getAllTeamsPlayedByTeam(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("adiciona um jogador a um time")
    public TeamPlayedDto createTeamPlayed(@RequestBody TeamPlayedDto teamPlayedDto) {
        return teamPlayedService.createTeamPlayed(teamPlayedDto);
    }

    @PutMapping("/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Atuliza o jogador em um time")
    public TeamPlayedDto updateTeamPlayed(@PathVariable Long id, @RequestBody TeamPlayedDto teamPlayedDto) {
        return teamPlayedService.updateTeamPlayed(id, teamPlayedDto);
    }

    @DeleteMapping("/{id}/inactive")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Retira um jogador de um time")
    public void deleteTeamPlayedDto(@PathVariable Long id) {
        teamPlayedService.deleteTeamPlayedDto(id);
    }
}
