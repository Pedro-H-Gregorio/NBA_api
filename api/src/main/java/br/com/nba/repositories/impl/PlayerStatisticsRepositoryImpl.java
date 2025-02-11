package br.com.nba.repositories.impl;

import br.com.nba.entities.PlayerStatistics;
import jakarta.persistence.EntityManagerFactory;

public class PlayerStatisticsRepositoryImpl extends RepositoryBaseImpl<PlayerStatistics, String>{
    public PlayerStatisticsRepositoryImpl(EntityManagerFactory emf) {
        super(PlayerStatistics.class, emf);
    }

    protected String getEntityName() {
        return "PLAYER_STATISTICS";
    }
}
