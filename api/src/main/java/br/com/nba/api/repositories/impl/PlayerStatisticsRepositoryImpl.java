package br.com.nba.api.repositories.impl;

import br.com.nba.api.entities.PlayerStatistics;
import br.com.nba.api.repositories.interfaces.PlayerStatisticsRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerStatisticsRepositoryImpl extends RepositoryBaseImpl<PlayerStatistics, String> implements PlayerStatisticsRepository {
    public PlayerStatisticsRepositoryImpl() {
        super(PlayerStatistics.class);
    }

    @Override
    protected String getEntityName() {
        return "PLAYER_STATISTICS";
    }
}
