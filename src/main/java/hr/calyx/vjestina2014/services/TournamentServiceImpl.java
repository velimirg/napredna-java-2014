package hr.calyx.vjestina2014.services;

import hr.calyx.vjestina2014.domain.*;
import hr.calyx.vjestina2014.repositories.TournamentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vgasparovic on 13/10/14.
 */
@Service
@Transactional
public class TournamentServiceImpl implements TournamentService {

    @Autowired
    MatchService matchService;

    @Autowired
    TournamentRepository tournamentRepository;

    @Autowired
    PlayerService playerService;
    @Override
    public Tournament getDummyTournament() {
        Tournament tournament = new Tournament();

        List<Player> players = playerService.list();

        tournamentRepository.save(tournament);

        tournament.setName("Dummy Tournament Template " + tournament.getId());

        int roundNum = 4;

        int gameNum = 5;

        tournament.setTemplate(true);
        tournament.setRounds(new ArrayList<Round>());
        for (int i = 0; i < roundNum; i++) {
            Round round = new Round();
            round.setDescription("Round " + (i + 1));
            round.setTournament(tournament);
            round.setMatches(new ArrayList<Match>());
            for (int j = 0; j <  Math.round(Math.pow(2, roundNum - i - 1)); j++) {
                Match match = new Match();
                match.setDescription("Match " + (j + 1));
                match.setRound(round);
                match.setGames(new ArrayList<Game>());
                if (i == 0) {
                    match.setPlayer1(players.get(j * 2));
                    match.setPlayer2(players.get(j * 2 + 1));
                }

                for (int k = 0; k < gameNum; k++) {
                    Game game = new Game();
                    game.setMap(Map.values()[k]);
                    game.setMatch(match);
                    game.setDate(new Date());
                    match.getGames().add(game);
                }
                round.getMatches().add(match);
            }
            tournament.getRounds().add(round);
        }

        return tournament;
    }

    @Override
    public List<Tournament> list() {
        return (List<Tournament>) tournamentRepository.findAll();
    }

    @Override
    public Tournament get(Long id) {
        return tournamentRepository.findOne(id);
    }

    @Override
    public Tournament create(Tournament tournament) {

        return tournamentRepository.save(tournament);
    }

    @Override
    public Tournament update(Long id, Tournament updatedTournament) {
        Tournament tournament = tournamentRepository.findOne(id);
        BeanUtils.copyProperties(updatedTournament, tournament, "id", "rounds");
        return tournament;
    }

    @Override
    public void delete(Long id) {
        tournamentRepository.delete(id);
    }

    @Override
    public List<Tournament> listTemplates() {
        return tournamentRepository.findByTemplate(true);
    }

    @Override
    public Tournament createFull(Tournament tournament) {

        for (Round round : tournament.getRounds()) {
            round.setTournament(tournament);
            for (Match match : round.getMatches()) {
                match.setRound(round);
                for (Game game : match.getGames()) {
                    game.setMatch(match);
                }
            }
        };

        return tournamentRepository.save(tournament);
    }

    @Override
    public Tournament createFromTemplate(Long templateId, Tournament tournament) {
        tournament.setParentTemplate(tournamentRepository.findOne(templateId));
        for (Round round : tournament.getRounds()) {
            round.setTournament(tournament);
            for (Match match : round.getMatches()) {
                match.setRound(round);
                for (Game game : match.getGames()) {
                    game.setMatch(match);
                }
            }
        };
        return tournamentRepository.save(tournament);

    }

    @Override
    public List<Tournament> listByTemplateId(Long templateId) {
        return tournamentRepository.findByParentTemplate_Id(templateId);
    }
}
