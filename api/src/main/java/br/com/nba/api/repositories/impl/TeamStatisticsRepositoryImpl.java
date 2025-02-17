package br.com.nba.api.repositories.impl;

import br.com.nba.api.entities.TeamStatistics;
import br.com.nba.api.repositories.interfaces.TeamStatisticsRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TeamStatisticsRepositoryImpl extends RepositoryBaseImpl<TeamStatistics, String> implements TeamStatisticsRepository {
    public TeamStatisticsRepositoryImpl() {
        super(TeamStatistics.class);
    }

    @Override
    protected String getEntityName() {
        return "TEAM_STATISTICS";
    }
}
