package hr.calyx.vjestina2014.controllers;

import hr.calyx.vjestina2014.domain.Tournament;
import hr.calyx.vjestina2014.services.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TournamentController {
    @Autowired
    TournamentService tournamentService;

    @RequestMapping(value = "/tournaments", method = RequestMethod.GET)
    public ResponseEntity listTournaments() {
        return new ResponseEntity(tournamentService.list(), HttpStatus.OK);
    }

    @RequestMapping(value = "/tournaments/{id}", method = RequestMethod.GET)
    public ResponseEntity getTournament(@PathVariable Long id) {
        return new ResponseEntity(tournamentService.get(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/tournaments", method = RequestMethod.POST)
    public ResponseEntity postTournament(@RequestBody Tournament tournament) {
        return new ResponseEntity(tournamentService.create(tournament), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/tournaments/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateTournament(@PathVariable Long id, @RequestBody Tournament tournament) {
        return new ResponseEntity(tournamentService.update(id, tournament), HttpStatus.OK);
    }

    @RequestMapping(value = "/tournaments/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteTournament(@PathVariable Long id) {
        tournamentService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
