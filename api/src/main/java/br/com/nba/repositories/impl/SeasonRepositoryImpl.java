package br.com.nba.repositories.impl;

import br.com.nba.entities.Season;
import br.com.nba.repositories.interfaces.SeasonRepository;
import jakarta.persistence.EntityManagerFactory;

public class SeasonRepositoryImpl extends RepositoryBaseImpl<Season, String> implements SeasonRepository {

    public SeasonRepositoryImpl(EntityManagerFactory emf) {
        super(Season.class, emf);
    }

    protected String getEntityName() {
        return "SEASON";
    }
}
