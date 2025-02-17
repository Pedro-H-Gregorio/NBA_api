package br.com.nba.api.repositories.impl;

import br.com.nba.api.entities.Season;
import br.com.nba.api.repositories.interfaces.SeasonRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SeasonRepositoryImpl extends RepositoryBaseImpl<Season, String> implements SeasonRepository {

    public SeasonRepositoryImpl() {
        super(Season.class);
    }

    @Override
    protected String getEntityName() {
        return "SEASON";
    }
}
