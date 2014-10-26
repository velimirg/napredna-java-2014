package hr.calyx.vjestina2014.controllers;

import hr.calyx.vjestina2014.domain.Round;
import hr.calyx.vjestina2014.services.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Tomislav on 10/19/2014.
 */
@Controller
public class RoundController {
    
    @Autowired
    RoundService roundService;

    @RequestMapping(value = "/tournaments/{tournamentId}/rounds", method = RequestMethod.GET)
    public ResponseEntity listRounds(@PathVariable Long tournamentId) {
        return new ResponseEntity(roundService.listByTournament(tournamentId), HttpStatus.OK);
    }

    @RequestMapping(value = "/tournaments/{tournamentId}/rounds/{id}", method = RequestMethod.GET)
    public ResponseEntity getRound(@PathVariable Long id) {
        return new ResponseEntity(roundService.get(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/tournaments/{tournamentId}/rounds", method = RequestMethod.POST)
    public ResponseEntity postRound(@RequestBody Round round, @PathVariable Long tournamentId) {
        return new ResponseEntity(roundService.create(round, tournamentId), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/tournaments/{tournamentId}/rounds/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteRound(@PathVariable Long id) {
        roundService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
