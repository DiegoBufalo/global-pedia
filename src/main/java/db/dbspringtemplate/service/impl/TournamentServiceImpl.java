package db.dbspringtemplate.service.impl;

import db.dbspringtemplate.dto.TournamentDto;
import db.dbspringtemplate.error.RestException;
import db.dbspringtemplate.model.Tournament;
import db.dbspringtemplate.repository.TournamentRepository;
import db.dbspringtemplate.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TournamentServiceImpl implements TournamentService {

    public static final String TOURNAMENT_NOT_FOUND = "Tournament not found";

    private final TournamentRepository tournamentRepository;

    @Autowired
    public TournamentServiceImpl (TournamentRepository tournamentRepository){
        this.tournamentRepository = tournamentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TournamentDto> getAllTournaments() {
        return TournamentDto.fromEntity(tournamentRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TournamentDto> getAllTournamentsByName(String name) {
        return TournamentDto.fromEntity(tournamentRepository.findAllByName(name));
    }

    @Override
    @Transactional(readOnly = true)
    public TournamentDto getTournament(Long id) {
        Tournament tournament = tournamentRepository.findById(id).orElseThrow(() -> {
            throw new RestException(HttpStatus.NOT_FOUND, TOURNAMENT_NOT_FOUND);
        });
        return TournamentDto.fromEntity(tournament);
    }

    @Override
    @Transactional
    public TournamentDto createTournament(TournamentDto tournamentDto) {

        List<Tournament> tournaments = tournamentRepository.findAllByNameAndStartDate(tournamentDto.getName(), tournamentDto.getStartDate());
        if (!tournaments.isEmpty())
            throw new RestException(HttpStatus.CONFLICT, "Torneio já registrado");

        Tournament tournament = Tournament.builder()
                .name(tournamentDto.getName())
                .startDate(tournamentDto.getStartDate())
                .endDate(tournamentDto.getEndDate())
                .winner(tournamentDto.getWinner())
                .tier(tournamentDto.getTier())
                .country(tournamentDto.getCountry())
                .mvp(tournamentDto.getMvp())
                .lan(tournamentDto.isLan())
                .build();

        return TournamentDto.fromEntity(tournamentRepository.save(tournament));
    }

    @Override
    @Transactional
    public TournamentDto updateTournament(Long id, TournamentDto tournamentDto) {
        Tournament tournament = tournamentRepository.findById(id).orElseThrow(() -> {
            throw new RestException(HttpStatus.NOT_FOUND, TOURNAMENT_NOT_FOUND);
        });

        if (!tournament.getName().equals(tournamentDto.getName()) ||
                !tournament.getStartDate().toString().equals(tournamentDto.getStartDate().toString())){
            List<Tournament> tournaments = tournamentRepository.findAllByNameAndStartDate(tournamentDto.getName(), tournamentDto.getStartDate());
            if (!tournaments.isEmpty())
                throw new RestException(HttpStatus.CONFLICT, "Torneio já registrado");
        }

        tournament.setName(tournamentDto.getName());
        tournament.setStartDate(tournamentDto.getStartDate());
        tournament.setEndDate(tournamentDto.getEndDate());
        tournament.setWinner(tournamentDto.getWinner());
        tournament.setTier(tournamentDto.getTier());
        tournament.setCountry(tournamentDto.getCountry());
        tournament.setMvp(tournamentDto.getMvp());
        tournament.setLan(tournamentDto.isLan());

        return TournamentDto.fromEntity(tournament);
    }

    @Override
    @Transactional
    public void inactiveTournament(Long id) {
        Tournament tournament = tournamentRepository.findById(id).orElseThrow(() -> {
            throw new RestException(HttpStatus.NOT_FOUND, TOURNAMENT_NOT_FOUND);
        });
        tournamentRepository.delete(tournament);
    }
}
