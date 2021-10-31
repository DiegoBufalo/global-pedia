package db.dbspringtemplate.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "player")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_id_generator")
    @SequenceGenerator(name = "player_id_generator", sequenceName = "player_sequence_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(targetEntity = TeamPlayed.class, mappedBy = "player", cascade = CascadeType.ALL)
    private List<TeamPlayed> teams;

    @Column(name = "active")
    private boolean active;
}
