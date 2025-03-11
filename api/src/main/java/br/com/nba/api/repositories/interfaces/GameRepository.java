package br.com.nba.api.repositories.interfaces;

import br.com.nba.api.entities.Game;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends RepositoryBase<Game, String>{
}
