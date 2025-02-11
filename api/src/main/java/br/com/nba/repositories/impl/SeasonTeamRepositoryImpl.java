package br.com.nba.repositories.impl;

import br.com.nba.entities.SeasonTeam;
import br.com.nba.repositories.interfaces.SeasonTeamRepository;
import jakarta.persistence.EntityManagerFactory;

public class SeasonTeamRepositoryImpl extends RepositoryBaseImpl<SeasonTeam, String> implements SeasonTeamRepository {
    public SeasonTeamRepositoryImpl(EntityManagerFactory emf) {
        super(SeasonTeam.class, emf);
    }

    protected String getEntityName() {
        return "SEASON_TEAM";
    }
}
