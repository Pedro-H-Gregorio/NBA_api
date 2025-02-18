package br.com.nba.api.repositories.interfaces;

import br.com.nba.api.entities.SeasonTeam;
import br.com.nba.api.repositories.PersistenciaDawException;

import java.util.List;

public interface SeasonTeamRepository extends RepositoryBase<SeasonTeam, String>{
    List<SeasonTeam> getAllTeamsBySeason(String seasonId) throws PersistenciaDawException;
}
