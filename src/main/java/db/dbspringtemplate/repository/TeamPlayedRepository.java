package db.dbspringtemplate.repository;

import db.dbspringtemplate.model.TeamPlayed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamPlayedRepository extends JpaRepository<TeamPlayed, Long> {
    @Query("select x.id from #{#entityName} x")
    List<Long> getAllIds();

    @Query("select x from #{#entityName} x where x.player.id = ?1")
    List<TeamPlayed> findAllByPlayer(Long id);

    @Query("select x from #{#entityName} x where x.team.id = ?1")
    List<TeamPlayed> findAllByTeam(Long id);
}
