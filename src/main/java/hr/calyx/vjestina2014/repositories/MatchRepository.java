package hr.calyx.vjestina2014.repositories;

import hr.calyx.vjestina2014.domain.Match;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Tomislav on 10/20/2014.
 */

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {
    List<Match> findByRoundId(Long roundId);
}
