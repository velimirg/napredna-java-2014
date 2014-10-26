package hr.calyx.vjestina2014.repositories;

import hr.calyx.vjestina2014.domain.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Tomislav on 10/20/2014.
 */
public interface GameRepository extends CrudRepository<Game, Long> {
    List<Game> findByMatchId(Long matchId);
}
