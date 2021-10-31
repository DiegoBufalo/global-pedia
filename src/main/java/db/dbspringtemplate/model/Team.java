package db.dbspringtemplate.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "team")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_id_generator")
    @SequenceGenerator(name = "team_id_generator", sequenceName = "team_sequence_id")
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "creation")
    private LocalDate creation;

    @Column(name = "active")
    private boolean active;
}
