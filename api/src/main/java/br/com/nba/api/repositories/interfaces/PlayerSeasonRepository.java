package br.com.nba.api.repositories.interfaces;

import br.com.nba.api.entities.PlayerSeason;
import br.com.nba.api.entities.SeasonTeam;
import br.com.nba.api.repositories.PersistenciaDawException;

import java.util.List;

public interface PlayerSeasonRepository extends RepositoryBase<PlayerSeason, String>{
    List<PlayerSeason> getAllPlayersBySeason(String seasonId) throws PersistenciaDawException;
}
