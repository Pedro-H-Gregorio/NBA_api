package br.com.nba.repositories.impl;

import br.com.nba.entities.Team;
import br.com.nba.repositories.interfaces.TeamRepository;
import jakarta.persistence.EntityManagerFactory;

public class TeamRepositoryImpl extends RepositoryBaseImpl<Team, Integer> implements TeamRepository {
    public TeamRepositoryImpl(EntityManagerFactory emf) {
        super(Team.class, emf);
    }

    protected String getEntityName() {
        return "TEAM";
    }
}
