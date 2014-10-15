package hr.calyx.vjestina2014.services;

import hr.calyx.vjestina2014.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {
    @Override
    public Match getDummyMatch() {
        Player player1 = new Player(1L, "Lee Young Ho", "FlasH", Race.Terran);
        Player player2 = new Player(2L, "Lee Jae Dong", "JaeDong", Race.Zerg);

        Match m = new Match();
        m.setPlayer1(player1);
        m.setPlayer2(player2);

        Game game1 = new Game(Map.OVERGROWTH, new Date());
        game1.setWinner(player2);

        Game game2 = new Game(Map.CATALLENA, new Date());
        game2.setWinner(player2);

        List<Game> games = new ArrayList();
        games.add(game1);
        games.add(game2);

        m.setGames(games);
        m.setWinner(player2);

        return m;
    }
}
