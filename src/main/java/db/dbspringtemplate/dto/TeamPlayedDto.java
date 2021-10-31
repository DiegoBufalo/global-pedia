package db.dbspringtemplate.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import db.dbspringtemplate.model.Player;
import db.dbspringtemplate.model.Team;
import db.dbspringtemplate.model.TeamPlayed;
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
public class TeamPlayedDto {

    private Long id;

    private Team team;

    private Player player;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean active;

    public static TeamPlayedDto fromEntity(TeamPlayed teamPlayed) {
        return TeamPlayedDto.builder()
                .id(teamPlayed.getId())
                .team(teamPlayed.getTeam())
                .player(teamPlayed.getPlayer())
                .startDate(teamPlayed.getStartDate())
                .endDate(teamPlayed.getEndDate())
                .active(teamPlayed.isActive())
                .build();
    }

    public static List<TeamPlayedDto> fromEntity(List<TeamPlayed> teamsPlayed) {
        return teamsPlayed.stream().map(TeamPlayedDto::fromEntity).collect(Collectors.toList());
    }
}
