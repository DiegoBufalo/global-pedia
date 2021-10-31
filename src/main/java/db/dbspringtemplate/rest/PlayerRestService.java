package db.dbspringtemplate.rest;

import db.dbspringtemplate.dto.PlayerDto;
import db.dbspringtemplate.service.PlayerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/players")
public class PlayerRestService {

    private final PlayerService playerService;

    @Autowired
    public PlayerRestService(PlayerService playerService) {
        this.playerService = playerService;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Busca todos os jogadores com filtros opcionais")
    public List<PlayerDto> getAllPlayers(@RequestParam(required = false) boolean active,
                                         @RequestParam(required = false) String name) {
        if (name == null)
            return playerService.getAllPlayers(active);
        return playerService.getAllPlayersByName(name);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Busca um jogador especifico")
    public PlayerDto getPlayer(@PathVariable Long id) {
        return playerService.getPlayer(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Adiciona um jogador a base de dados")
    public PlayerDto createPlayer(@RequestBody PlayerDto playerDto) {
        return playerService.createPlayer(playerDto);
    }

    @PutMapping("/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Atualiza um jogador existente na base de dados")
    public PlayerDto updatePlayer(@PathVariable Long id, @RequestBody PlayerDto playerDto) {
        return playerService.updatePlayer(id, playerDto);
    }

    @DeleteMapping("/{id}/inactive")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Inativa um jogador")
    public void inactivePlayer(@PathVariable Long id) {
        playerService.inactivePlayer(id);
    }

    @PutMapping("/{id}/reactive")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Reativa um jogador")
    public void reactivePlayer(@PathVariable Long id) {
        playerService.reactivePlayer(id);
    }
}
