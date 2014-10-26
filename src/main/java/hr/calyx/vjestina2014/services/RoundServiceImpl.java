package hr.calyx.vjestina2014.services;

import hr.calyx.vjestina2014.domain.Round;
import hr.calyx.vjestina2014.domain.Tournament;
import hr.calyx.vjestina2014.repositories.RoundRepository;
import hr.calyx.vjestina2014.repositories.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Tomislav on 10/19/2014.
 */
@Service
@Transactional
public class RoundServiceImpl implements RoundService {

    @Autowired
    RoundRepository roundRepository;

    @Autowired
    TournamentRepository tournamentRepository;

    @Override
    public List<Round> listByTournament(Long tournamentId) {
        return roundRepository.findByTournamentId(tournamentId);
    }

    @Override
    public Round get(Long id) {
        return roundRepository.findOne(id);
    }

    @Override
    public Round create(Round round, Long tournamentId) {
        round.setTournament(tournamentRepository.findOne(tournamentId));
        return roundRepository.save(round);
    }

    @Override
    public Round update(Long id, Round updatedRound) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void delete(Long id) {
        roundRepository.delete(id);
    }
}
