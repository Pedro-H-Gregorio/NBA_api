package br.com.nba.api.repositories.impl;

import br.com.nba.api.entities.Game;
import br.com.nba.api.repositories.interfaces.GameRepository;
import org.springframework.stereotype.Repository;

@Repository
public class GameRepositoryImpl extends RepositoryBaseImpl<Game, String> implements GameRepository {
    public GameRepositoryImpl() {
        super(Game.class);
    }

    @Override
    protected String getEntityName() {
        return "GAME";
    }
}
