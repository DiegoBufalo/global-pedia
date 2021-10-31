package db.dbspringtemplate.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class TeamDto {

    private Long id;

    private String name;

    private LocalDate creation;

    private List<Tournament> tournaments;

    private boolean active;


    public static TeamDto fromEntity(Team teams) {
        return TeamDto.builder()
                .id(teams.getId())
                .name(teams.getName())
                .creation(teams.getCreation())
                .active(teams.isActive())
                .build();
    }

    public static List<TeamDto> fromEntity(List<Team> teams) {
        return teams.stream().map(TeamDto::fromEntity).collect(Collectors.toList());
    }
}
