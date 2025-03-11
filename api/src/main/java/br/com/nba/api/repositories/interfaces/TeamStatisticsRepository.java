package br.com.nba.api.repositories.interfaces;

import br.com.nba.api.entities.TeamStatistics;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamStatisticsRepository extends RepositoryBase<TeamStatistics, String> {
}
