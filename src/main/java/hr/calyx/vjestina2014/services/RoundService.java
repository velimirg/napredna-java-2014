package hr.calyx.vjestina2014.services;

import hr.calyx.vjestina2014.domain.Round;

import java.util.List;

/**
 * Created by Tomislav on 10/19/2014.
 */

public interface RoundService {

    public List<Round> listByTournament(Long tournamentId);

    public Round get(Long id);

    public Round create(Round round, Long tournamentId);

    public Round update(Long id, Round updatedRound);

    public void delete(Long id);

}
