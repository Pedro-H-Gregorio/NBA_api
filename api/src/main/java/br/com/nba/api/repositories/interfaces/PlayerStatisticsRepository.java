package br.com.nba.api.repositories.interfaces;

import br.com.nba.api.entities.PlayerStatistics;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerStatisticsRepository extends RepositoryBase<PlayerStatistics, String> {
}
