package db.dbspringtemplate.repository;

import db.dbspringtemplate.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query("select x.id from Team x")
    List<Long> getAllIds();

    @Query("select x from Team x where x.active = true")
    List<Team> findAllActive();

    @Query("select x from Team x where x.name = ?1")
    List<Team> findAllByName(String name);
}
