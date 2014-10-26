package hr.calyx.vjestina2014.services;

import hr.calyx.vjestina2014.domain.Game;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Tomislav on 10/20/2014.
 */
public interface GameService {

    public Game get(Long id);

    public List<Game> listByMatch(Long matchId);

    public Game create(Long matchId, Game game);

    public Game update(Long id, Game updatedGame);

    public void delete(Long id);


}
