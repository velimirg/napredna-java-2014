package hr.calyx.vjestina2014.services;

import hr.calyx.vjestina2014.domain.Game;
import hr.calyx.vjestina2014.domain.Race;
import hr.calyx.vjestina2014.repositories.GameRepository;
import hr.calyx.vjestina2014.repositories.MatchRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Tomislav on 10/20/2014.
 */
@Transactional
@Service
public class GameServiceImpl implements GameService {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    MatchRepository matchRepository;

    @Override
    public Game get(Long id) {
        return gameRepository.findOne(id);
    }

    @Override
    public List<Game> listByMatch(Long matchId) {
        return gameRepository.findByMatchId(matchId);
    }

    @Override
    public Game create(Long matchId, Game game) {
        game.setMatch(matchRepository.findOne(matchId));
        return gameRepository.save(game);
    }

    @Override
    public Game update(Long id, Game updatedGame) {
        Game game = gameRepository.findOne(id);
        BeanUtils.copyProperties(updatedGame, game, "id", "match");
        return game;
    }

    @Override
    public void delete(Long id) {
        gameRepository.delete(id);
    }
}
