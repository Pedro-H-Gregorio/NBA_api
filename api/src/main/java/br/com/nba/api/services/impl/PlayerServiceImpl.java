package br.com.nba.api.services.impl;

import br.com.nba.api.entities.Player;
import br.com.nba.api.repositories.interfaces.PlayerRepository;
import br.com.nba.api.services.interfaces.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl extends ServiceBaseImpl<Player, Integer> implements PlayerService {

    @Autowired
    public PlayerServiceImpl(@Qualifier("playerRepository") PlayerRepository repository) {
        super(repository);
    }
}
