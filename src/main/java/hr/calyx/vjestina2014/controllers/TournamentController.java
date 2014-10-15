package hr.calyx.vjestina2014.controllers;

import hr.calyx.vjestina2014.domain.Tournament;
import hr.calyx.vjestina2014.services.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TournamentController {
    @Autowired
    TournamentService tournamentService;

    @RequestMapping(value="/dummytournament", method = RequestMethod.GET)
    public ResponseEntity<Tournament> getDummyTournament() {
        return new ResponseEntity<Tournament>(
          tournamentService.getDummyTournament(),
          HttpStatus.OK
        );
    }
}
