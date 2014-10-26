package hr.calyx.vjestina2014.services;

import hr.calyx.vjestina2014.domain.Match;
import hr.calyx.vjestina2014.domain.Round;
import hr.calyx.vjestina2014.domain.Tournament;
import hr.calyx.vjestina2014.repositories.TournamentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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

    @Override
    public Tournament getDummyTournament() {
        Tournament tourney = new Tournament();
        List<Round> rounds = new ArrayList<Round>();

        Round finals = new Round();
        List<Match> matches = new ArrayList<Match>();
        matches.add(matchService.getDummyMatch());
        finals.setMatches(matches);
        rounds.add(finals);

        Round semis = new Round();
        matches = new ArrayList<Match>();
        for (int i=0; i<2; i++) {
            matches.add(matchService.getDummyMatch());
        }
        semis.setMatches(matches);
        rounds.add(semis);

        tourney.setRounds(rounds);
        return tourney;
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
}
