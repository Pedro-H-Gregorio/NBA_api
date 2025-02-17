package br.com.nba.api.repositories.impl;

import br.com.nba.api.entities.SeasonTeam;
import br.com.nba.api.repositories.interfaces.SeasonTeamRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SeasonTeamRepositoryImpl extends RepositoryBaseImpl<SeasonTeam, String> implements SeasonTeamRepository {
    public SeasonTeamRepositoryImpl() {
        super(SeasonTeam.class);
    }

    @Override
    protected String getEntityName() {
        return "SEASON_TEAM";
    }
}
