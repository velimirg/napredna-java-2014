package hr.calyx.vjestina2014.controllers;

import hr.calyx.vjestina2014.domain.DummyClass;
import hr.calyx.vjestina2014.domain.Match;
import hr.calyx.vjestina2014.domain.Tournament;
import hr.calyx.vjestina2014.services.DummyService;
import hr.calyx.vjestina2014.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DummyController {

    @Autowired
    DummyService dummyService;

    @Autowired
    MatchService matchService;

    @RequestMapping(value="/dummy", method = RequestMethod.GET)
    @ResponseBody
    public String dummy() {
        return dummyService.getDummyString();
    }

    @RequestMapping(value="/dummyobject", method=RequestMethod.GET)
    public ResponseEntity<DummyClass> getDummyObject() {
        return new ResponseEntity<DummyClass>(dummyService.getDummyObject(), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value="/randommatch", method = RequestMethod.GET)
    public ResponseEntity<Match> getRandomMatch() {
        return new ResponseEntity<Match>(matchService.getDummyMatch(), HttpStatus.OK);
    }

    @RequestMapping(value="/randomtournament", method = RequestMethod.GET)
    @ResponseBody
    public String getRandomTournament() {
        Tournament tourney = new Tournament();

        return tourney.toString();
    }

}
