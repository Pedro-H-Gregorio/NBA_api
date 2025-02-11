package br.com.nba.repositories.impl;

import br.com.nba.entities.PlayerSeason;
import br.com.nba.repositories.interfaces.PlayerSeasonRepository;
import jakarta.persistence.EntityManagerFactory;

public class PlayerSeasonRepositoryImpl extends RepositoryBaseImpl<PlayerSeason, String> implements PlayerSeasonRepository {
    public PlayerSeasonRepositoryImpl(EntityManagerFactory emf) {
        super(PlayerSeason.class, emf);
    }

    protected String getEntityName() {
        return "PLAYER_SEASON";
    }
}
