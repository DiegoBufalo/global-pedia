package db.dbspringtemplate.service.impl;

import db.dbspringtemplate.dto.PlayerDto;
import db.dbspringtemplate.error.RestException;
import db.dbspringtemplate.model.Player;
import db.dbspringtemplate.repository.PlayerRepository;
import db.dbspringtemplate.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    public static final String PLAYER_NOT_FOUND = "Player not found";

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlayerDto> getAllPlayers(boolean active) {
        if (active)
            return PlayerDto.fromEntity(playerRepository.findAllActive());

        return PlayerDto.fromEntity(playerRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlayerDto> getAllPlayersByName(String name) {
        return PlayerDto.fromEntity(playerRepository.findByName(name));
    }

    @Override
    @Transactional(readOnly = true)
    public PlayerDto getPlayer(Long id) {
        Player player = playerRepository.findById(id).orElseThrow(() -> {
            throw new RestException(HttpStatus.NOT_FOUND, PLAYER_NOT_FOUND);
        });
        return PlayerDto.fromEntity(player);
    }

    @Override
    @Transactional
    public PlayerDto createPlayer(PlayerDto playerDto) {
        List<Player> players = playerRepository.findByName(playerDto.getName());
        if (!players.isEmpty())
            throw new RestException(HttpStatus.CONFLICT, "Nome de jogador já cadastrado");

        Player player = Player.builder()
                .id(playerDto.getId())
                .name(playerDto.getName())
                .teams(playerDto.getTeams())
                .active(true)
                .build();

        return PlayerDto.fromEntity(playerRepository.save(player));
    }

    @Override
    @Transactional
    public PlayerDto updatePlayer(Long id, PlayerDto playerDto) {
        Player player = playerRepository.findById(id).orElseThrow(() -> {
            throw new RestException(HttpStatus.NOT_FOUND, PLAYER_NOT_FOUND);
        });

        if (!player.getName().equals(playerDto.getName())) {
            List<Player> players = playerRepository.findByName(playerDto.getName());
            if (!players.isEmpty()) {
                throw new RestException(HttpStatus.CONFLICT, "Nome de jogador já cadastrado");
            }
        }

        player.setName(playerDto.getName());

        return PlayerDto.fromEntity(player);
    }

    @Override
    @Transactional
    public void inactivePlayer(Long id) {
        Player player = playerRepository.findById(id).orElseThrow(() -> {
            throw new RestException(HttpStatus.NOT_FOUND, PLAYER_NOT_FOUND);
        });
        player.setActive(false);
    }

    @Override
    @Transactional
    public void reactivePlayer(Long id) {
        Player player = playerRepository.findById(id).orElseThrow(() -> {
            throw new RestException(HttpStatus.NOT_FOUND, PLAYER_NOT_FOUND);
        });
        player.setActive(true);
    }
}
