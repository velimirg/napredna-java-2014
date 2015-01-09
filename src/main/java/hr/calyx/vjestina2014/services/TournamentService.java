package hr.calyx.vjestina2014.services;

import hr.calyx.vjestina2014.domain.Tournament;

import java.util.List;

public interface TournamentService {
    public Tournament getDummyTournament();

    public List<Tournament> list();

    public Tournament get(Long id);

    public Tournament create(Tournament tournament);

    public Tournament update(Long id, Tournament updatedTournament);

    public void delete(Long id);

    public List<Tournament> listTemplates();

    public Tournament createFull(Tournament tournament);
}
