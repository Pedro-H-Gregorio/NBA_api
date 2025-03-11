package br.com.nba.api.services.impl;

import br.com.nba.api.entities.TeamStatistics;
import br.com.nba.api.repositories.interfaces.TeamStatisticsRepository;
import br.com.nba.api.services.interfaces.TeamStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TeamStatisticsServiceImpl extends ServiceBaseImpl<TeamStatistics, String> implements TeamStatisticsService {

    @Autowired
    public TeamStatisticsServiceImpl(@Qualifier("teamStatisticsRepository") TeamStatisticsRepository repository) {
        super(repository);
    }
}
