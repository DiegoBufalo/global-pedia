package db.dbspringtemplate.service;

import db.dbspringtemplate.dto.PlayerDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlayerService {

    List<PlayerDto> getAllPlayers(boolean active);

    List<PlayerDto> getAllPlayersByName(String name);

    PlayerDto getPlayer(Long id);

    PlayerDto createPlayer (PlayerDto playerDto);

    PlayerDto updatePlayer (Long id, PlayerDto playerDto);

    void inactivePlayer(Long id);

    void reactivePlayer(Long id);
}
