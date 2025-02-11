package br.com.nba.repositories.impl;

import br.com.nba.entities.Game;
import br.com.nba.repositories.interfaces.GameRepository;
import jakarta.persistence.EntityManagerFactory;

public class GameRepositoryImpl extends RepositoryBaseImpl<Game, String> implements GameRepository {
    public GameRepositoryImpl(EntityManagerFactory emf) {
        super(Game.class, emf);
    }

    protected String getEntityName() {
        return "GAME";
    }
}
