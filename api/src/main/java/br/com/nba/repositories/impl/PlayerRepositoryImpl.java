package br.com.nba.repositories.impl;

import br.com.nba.entities.Player;
import br.com.nba.repositories.interfaces.PlayerRepository;
import jakarta.persistence.EntityManagerFactory;

public class PlayerRepositoryImpl extends RepositoryBaseImpl<Player, Integer> implements PlayerRepository {
    public PlayerRepositoryImpl(EntityManagerFactory emf) {
        super(Player.class, emf);
    }

    protected String getEntityName() {
        return "PLAYER";
    }
}
