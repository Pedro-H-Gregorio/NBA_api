package br.com.nba.api.repositories.interfaces;

import br.com.nba.api.entities.Player;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends RepositoryBase<Player, Integer>{
}
