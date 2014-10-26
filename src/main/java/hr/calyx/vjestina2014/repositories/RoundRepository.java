package hr.calyx.vjestina2014.repositories;

import hr.calyx.vjestina2014.domain.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Tomislav on 10/19/2014.
 */
@Repository
public interface RoundRepository extends CrudRepository<Round, Long> {

    public List<Round> findByTournamentId(Long tournamentId);
}
