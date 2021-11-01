package db.dbspringtemplate.rest;

import db.dbspringtemplate.dto.TeamDto;
import db.dbspringtemplate.service.TeamService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/teams")
public class TeamRestService extends AllowCorsService{

    private final TeamService teamService;

    @Autowired
    public TeamRestService(TeamService teamService) {
        this.teamService = teamService;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Busca todos os times")
    public List<TeamDto> getAllTeams(@RequestParam(required = false) boolean active,
                                     @RequestParam(required = false) String name) {
        if (name == null)
            return teamService.getAllTeams(active);
        return teamService.getAllTeamsByName(name);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Busca um time")
    public TeamDto getTeam(@PathVariable Long id) {
        return teamService.getTeam(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Cria um time")
    public TeamDto createTeam(@RequestBody TeamDto teamDto) {
        return teamService.createTeam(teamDto);
    }

    @PutMapping("/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Atualiza um time")
    public TeamDto updateTeam(@PathVariable Long id, @RequestBody TeamDto teamDto) {
        return teamService.updateTeam(id, teamDto);
    }

    @DeleteMapping("/{id}/inactive")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Inativa um time")
    public void inactiveTeam(@PathVariable Long id) {
        teamService.inactiveTeam(id);
    }

    @PutMapping("/{id}/reactive")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Reativa um time")
    public void reactiveTeam(@PathVariable Long id) {
        teamService.reactiveTeam(id);
    }
}
