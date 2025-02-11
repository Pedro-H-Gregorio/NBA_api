package br.com.nba.repositories.impl;

import br.com.nba.entities.TeamStatistics;
import br.com.nba.repositories.interfaces.TeamStatisticsRepository;
import jakarta.persistence.EntityManagerFactory;

public class TeamStatisticsRepositoryImpl extends RepositoryBaseImpl<TeamStatistics, String> implements TeamStatisticsRepository {
    public TeamStatisticsRepositoryImpl(EntityManagerFactory emf) {
        super(TeamStatistics.class, emf);
    }

    protected String getEntityName() {
        return "TEAM_STATISTICS";
    }
}
