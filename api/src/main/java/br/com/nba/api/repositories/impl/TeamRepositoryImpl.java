package br.com.nba.api.repositories.impl;

import br.com.nba.api.entities.Team;
import br.com.nba.api.repositories.interfaces.TeamRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TeamRepositoryImpl extends RepositoryBaseImpl<Team, Integer> implements TeamRepository {
    public TeamRepositoryImpl() {
        super(Team.class);
    }

    @Override
    protected String getEntityName() {
        return "TEAM";
    }
}
