package db.dbspringtemplate.repository;

import db.dbspringtemplate.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    @Query("select x from #{#entityName} x where x.name = ?1")
    List<Tournament> findAllByName(String name);

    @Query("select x from #{#entityName} x where x.name = ?1 and x.startDate = ?2")
    List<Tournament> findAllByNameAndStartDate(String name, LocalDate startDate);
}
