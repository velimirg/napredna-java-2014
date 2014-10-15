package hr.calyx.vjestina2014.services;

import hr.calyx.vjestina2014.domain.Match;
import hr.calyx.vjestina2014.domain.Round;
import hr.calyx.vjestina2014.domain.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vgasparovic on 13/10/14.
 */
@Service
public class TournamentServiceImpl implements TournamentService {

    @Autowired
    MatchService matchService;

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
}
