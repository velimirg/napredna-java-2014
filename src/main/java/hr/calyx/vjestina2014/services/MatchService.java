package hr.calyx.vjestina2014.services;

import hr.calyx.vjestina2014.domain.Match;

import java.util.List;

public interface MatchService {
    public Match getDummyMatch();

    public List<Match> listByRound(Long roundId);

    public Match get(Long id);

    public Match create(Long roundId, Match match);

    public Match update(Long id, Match updatedMatch);

    public void delete(Long id);
}
