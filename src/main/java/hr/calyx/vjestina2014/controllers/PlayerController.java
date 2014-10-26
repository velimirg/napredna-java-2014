package hr.calyx.vjestina2014.controllers;

import hr.calyx.vjestina2014.domain.Player;
import hr.calyx.vjestina2014.services.PlayerService;
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
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @RequestMapping(value = "/players/{id}", method = RequestMethod.GET)
    public ResponseEntity getPlayer(@PathVariable Long id) {
        return new ResponseEntity(playerService.get(id), HttpStatus.OK);
    }
    @RequestMapping(value = "/players", method = RequestMethod.GET)
    public ResponseEntity listPlayers() {
        return new ResponseEntity(playerService.list(), HttpStatus.OK);
    }

    @RequestMapping(value = "/players", method = RequestMethod.POST)
    public ResponseEntity createPlayer(@RequestBody Player player) {
        return new ResponseEntity(playerService.create(player), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/players/{id}", method = RequestMethod.PUT)
    public ResponseEntity updatePlayer(@PathVariable Long id, @RequestBody Player player) {
        return new ResponseEntity(playerService.update(id, player), HttpStatus.OK);
    }

    @RequestMapping(value = "/players/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deletePlayer(@PathVariable Long id) {
        playerService.delete(id);
        return new ResponseEntity( HttpStatus.OK);
    }
}
