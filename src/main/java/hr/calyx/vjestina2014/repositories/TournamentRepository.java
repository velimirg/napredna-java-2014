package hr.calyx.vjestina2014.repositories;

import hr.calyx.vjestina2014.domain.Tournament;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Tomislav on 10/19/2014.
 */
@Repository
public interface TournamentRepository extends CrudRepository<Tournament, Long> {

    List<Tournament> findByTemplate(boolean b);
}
