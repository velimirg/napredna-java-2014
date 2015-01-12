package hr.calyx.vjestina2014.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
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

import java.util.List;

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

        Tournament tournament = tournamentService.get(id);
        ObjectMapper mapper = new ObjectMapper();

        Hibernate4Module hbm = new Hibernate4Module();
        hbm.enable(Hibernate4Module.Feature.FORCE_LAZY_LOADING);

        mapper.registerModule(hbm);
        ObjectWriter w = mapper.writer();
        String result = "";
        try {
            result = w.writeValueAsString(tournament);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new ResponseEntity(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/tournaments", method = RequestMethod.POST)
    public ResponseEntity postTournament(@RequestBody Tournament tournament) {
        return new ResponseEntity(tournamentService.create(tournament), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/tournaments-full", method = RequestMethod.POST)
    public ResponseEntity postTournamentFull(@RequestBody Tournament tournament) {
        return new ResponseEntity(tournamentService.createFull(tournament), HttpStatus.CREATED);
    }


    @RequestMapping(value = "/tournaments-dummy", method = RequestMethod.POST)
    public ResponseEntity generateDummyTournament() {
        return new ResponseEntity(tournamentService.getDummyTournament(), HttpStatus.CREATED);
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


    @RequestMapping(value = "/tournament-templates", method = RequestMethod.GET)
    public ResponseEntity listTournamentTemplates() {

        List<Tournament> tournamentList = tournamentService.listTemplates();

        return new ResponseEntity(tournamentList, HttpStatus.OK);
    }

    @RequestMapping(value = "/tournament-templates/{templateId}/tournaments", method = RequestMethod.GET)
    public ResponseEntity getTournamentsFromTtemplate(@PathVariable Long templateId) {




        return new ResponseEntity(tournamentService.listByTemplateId(templateId), HttpStatus.OK);
    }

    @RequestMapping(value = "/tournament-templates/{templateId}/tournaments", method = RequestMethod.POST)
    public ResponseEntity postTournamentFromTemplate(@PathVariable Long templateId, @RequestBody Tournament tournament) {



        return new ResponseEntity(tournamentService.createFromTemplate(templateId, tournament), HttpStatus.OK);
    }

}
