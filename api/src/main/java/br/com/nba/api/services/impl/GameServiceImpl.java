package br.com.nba.api.services.impl;

import br.com.nba.api.entities.Game;
import br.com.nba.api.repositories.interfaces.GameRepository;
import br.com.nba.api.services.interfaces.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl extends ServiceBaseImpl<Game, String> implements GameService {

    @Autowired
    public GameServiceImpl(@Qualifier("gameRepository") GameRepository gameRepository) {
        super(gameRepository);
    }
}
