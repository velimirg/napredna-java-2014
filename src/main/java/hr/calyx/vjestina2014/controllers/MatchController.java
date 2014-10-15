package hr.calyx.vjestina2014.controllers;

import hr.calyx.vjestina2014.domain.Match;
import hr.calyx.vjestina2014.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MatchController {
    @Autowired
    MatchService matchService;

    @RequestMapping(value="/dummymatch", method = RequestMethod.GET)
    public ResponseEntity<Match> getRandomMatch() {
        return new ResponseEntity<Match>(matchService.getDummyMatch(), HttpStatus.OK);
    }

}
