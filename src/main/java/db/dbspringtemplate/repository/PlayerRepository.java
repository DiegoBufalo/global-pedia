package db.dbspringtemplate.repository;

import db.dbspringtemplate.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Query("select x from Player x where x.name = ?1")
    List<Player> findByName(String name);

    @Query("select x from Player x where x.active = true")
    List<Player> findAllActive();
}
