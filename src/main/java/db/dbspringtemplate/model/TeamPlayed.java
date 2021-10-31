package db.dbspringtemplate.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "team_played")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class TeamPlayed {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_played_id_generator")
    @SequenceGenerator(name = "team_played_id_generator", sequenceName = "team_played_sequence_id")
    private Long id;

    @JoinColumn(name = "team_id", referencedColumnName = "id")
    @OneToOne(targetEntity = Team.class)
    private Team team;

    @JoinColumn(name = "player_id")
    @ManyToOne(targetEntity = Player.class)
    private Player player;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "active")
    private boolean active;
}
