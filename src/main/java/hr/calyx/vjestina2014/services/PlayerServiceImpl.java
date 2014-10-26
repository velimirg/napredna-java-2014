package hr.calyx.vjestina2014.services;

import hr.calyx.vjestina2014.domain.Player;
import hr.calyx.vjestina2014.repositories.PlayerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Tomislav on 10/20/2014.
 */

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public Player get(Long id) {
        return playerRepository.findOne(id);
    }

    @Override
    public List<Player> list() {
        return (List<Player>) playerRepository.findAll();
    }

    @Override
    public Player create(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Player update(Long id, Player updatedPlayer) {
        Player player = playerRepository.findOne(id);
        BeanUtils.copyProperties(updatedPlayer, player, "id", "wonMatches");
        return player;
    }

    @Override
    public void delete(Long id) {
        playerRepository.delete(id);
    }

}
