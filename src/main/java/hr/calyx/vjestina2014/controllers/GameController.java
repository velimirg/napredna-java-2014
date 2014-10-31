package hr.calyx.vjestina2014.controllers;

import hr.calyx.vjestina2014.domain.Game;
import hr.calyx.vjestina2014.domain.Match;
import hr.calyx.vjestina2014.repositories.GameRepository;
import hr.calyx.vjestina2014.services.GameService;
import hr.calyx.vjestina2014.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Tomislav on 10/20/2014.
 */
@Controller
public class GameController {

    @Autowired
    GameService gameService;

    @RequestMapping(value = "/tournaments/{tournamentId}/rounds/{roundId}/matches/{matchId}/games", method = RequestMethod.GET)
    public ResponseEntity listGames(@PathVariable Long matchId) {
        return new ResponseEntity(gameService.listByMatch(matchId), HttpStatus.OK);
    }

    @RequestMapping(value = "/tournaments/{tournamentId}/rounds/{roundId}/matches/{matchId}/games", method = RequestMethod.POST)
    public ResponseEntity postGame(@PathVariable Long matchId, @RequestBody Game game) {
        return new ResponseEntity(gameService.create(matchId, game), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/tournaments/{tournamentId}/rounds/{roundId}/matches/{matchId}/games/{id}", method = RequestMethod.GET)
    public ResponseEntity getGame(@PathVariable Long id) {
        return new ResponseEntity(gameService.get(id), HttpStatus.OK);
    }
}
