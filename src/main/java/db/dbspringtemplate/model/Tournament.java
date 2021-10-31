package db.dbspringtemplate.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tournament")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tournament_id_generator")
    @SequenceGenerator(name = "tournament_id_generator", sequenceName = "tournament_sequence_id")
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @JoinColumn(name = "winner_id", referencedColumnName = "id")
    @OneToOne(targetEntity = Team.class, cascade = CascadeType.ALL)
    private Team winner;

    @Column(name = "tier")
    private Long tier;

    @Column(name = "country")
    private String country;

    @JoinColumn(name = "mvp_id", referencedColumnName = "id")
    @OneToOne(targetEntity = Player.class, cascade = CascadeType.ALL)
    private Player mvp;

    @Column(name = "lan")
    private boolean lan;

    @ElementCollection(targetClass = Team.class)
    @OneToMany(targetEntity = Team.class, fetch = FetchType.EAGER)
    @JoinTable(name = "tournament_team", joinColumns = {
            @JoinColumn(name = "tournament_id", referencedColumnName = "id")
    })
    @JoinColumn(name = "teams_id", referencedColumnName = "id")
    List<Team> teams;
}
