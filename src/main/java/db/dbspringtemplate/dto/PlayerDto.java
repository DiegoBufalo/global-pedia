package db.dbspringtemplate.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import db.dbspringtemplate.model.Player;
import db.dbspringtemplate.model.TeamPlayed;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayerDto {

    private Long id;

    private String name;

    private List<TeamPlayed> teams;

    private boolean active;

    public static PlayerDto fromEntity(Player player){
        return PlayerDto.builder()
                .id(player.getId())
                .name(player.getName())
                .teams(player.getTeams())
                .active(player.isActive())
                .build();
    }

    public static List<PlayerDto> fromEntity(List<Player> players){
        return players.stream().map(PlayerDto::fromEntity).collect(Collectors.toList());
    }
}
