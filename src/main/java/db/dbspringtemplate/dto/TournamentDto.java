package db.dbspringtemplate.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import db.dbspringtemplate.model.Player;
import db.dbspringtemplate.model.Team;
import db.dbspringtemplate.model.Tournament;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TournamentDto {

    private Long id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private Team winner;

    private Long tier;

    private String country;

    private Player mvp;

    private boolean lan;

    public static TournamentDto fromEntity(Tournament tournament) {
        return TournamentDto.builder()
                .id(tournament.getId())
                .name(tournament.getName())
                .startDate(tournament.getStartDate())
                .endDate(tournament.getEndDate())
                .winner(tournament.getWinner())
                .tier(tournament.getTier())
                .country(tournament.getCountry())
                .mvp(tournament.getMvp())
                .lan(tournament.isLan())
                .build();
    }

    public static List<TournamentDto> fromEntity(List<Tournament> tournaments) {
        return tournaments.stream().map(TournamentDto::fromEntity).collect(Collectors.toList());
    }
}
