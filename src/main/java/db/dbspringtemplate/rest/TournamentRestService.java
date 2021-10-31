package db.dbspringtemplate.rest;

import db.dbspringtemplate.dto.TournamentDto;
import db.dbspringtemplate.service.TournamentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/tournaments")
public class TournamentRestService {

    private final TournamentService tournamentService;

    @Autowired
    public TournamentRestService(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TournamentDto> getAllTournaments(@RequestParam(required = false) String name) {
        if (name == null)
            return tournamentService.getAllTournaments();
        return tournamentService.getAllTournamentsByName(name);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Busca um torneio")
    public TournamentDto getTournament(@PathVariable Long id) {
        return tournamentService.getTournament(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Cria um torneio")
    public TournamentDto createTournament(@RequestBody TournamentDto tournamentDto) {
        return tournamentService.createTournament(tournamentDto);
    }

    @PutMapping("/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Atuliza um torneio")
    public TournamentDto updateTournament(@PathVariable Long id, @RequestBody TournamentDto tournamentDto) {
        return tournamentService.updateTournament(id, tournamentDto);
    }

    @DeleteMapping("/{id}/inactive")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Inativa um torneio")
    public void inactiveTournament(@PathVariable Long id) {
        tournamentService.inactiveTournament(id);
    }
}
