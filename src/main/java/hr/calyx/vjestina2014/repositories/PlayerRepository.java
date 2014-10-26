package hr.calyx.vjestina2014.repositories;

import hr.calyx.vjestina2014.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Tomislav on 10/19/2014.
 */
@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
}
