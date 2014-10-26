package hr.calyx.vjestina2014.services;

import hr.calyx.vjestina2014.domain.Player;

import java.util.List;

/**
 * Created by Tomislav on 10/19/2014.
 */

public interface PlayerService {

    public Player get(Long id);

    public List<Player> list();

    public Player create(Player player);

    public Player update(Long id, Player updatedPlayer);

    public void delete(Long id);

}
