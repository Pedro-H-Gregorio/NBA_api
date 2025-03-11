package br.com.nba.api.repositories.interfaces;

import br.com.nba.api.entities.Team;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends RepositoryBase<Team, Integer> {
}
