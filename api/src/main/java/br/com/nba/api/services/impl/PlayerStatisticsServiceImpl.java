package br.com.nba.api.services.impl;

import br.com.nba.api.entities.PlayerStatistics;
import br.com.nba.api.repositories.interfaces.PlayerStatisticsRepository;
import br.com.nba.api.services.interfaces.PlayerStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PlayerStatisticsServiceImpl extends ServiceBaseImpl<PlayerStatistics, String> implements PlayerStatisticsService {

    @Autowired
    public PlayerStatisticsServiceImpl(@Qualifier("playerStatisticsRepository") PlayerStatisticsRepository repository) {
        super(repository);
    }
}
