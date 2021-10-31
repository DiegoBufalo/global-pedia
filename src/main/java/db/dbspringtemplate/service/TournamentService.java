package db.dbspringtemplate.service;

import db.dbspringtemplate.dto.TournamentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TournamentService {

    List<TournamentDto> getAllTournaments();

    List<TournamentDto> getAllTournamentsByName(String name);

    TournamentDto getTournament(Long id);

    TournamentDto createTournament(TournamentDto tournamentDto);

    TournamentDto updateTournament(Long id, TournamentDto tournamentDto);

    void inactiveTournament(Long id);
}
