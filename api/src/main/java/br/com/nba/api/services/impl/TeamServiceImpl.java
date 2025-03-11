package br.com.nba.api.services.impl;

import br.com.nba.api.entities.Team;
import br.com.nba.api.repositories.interfaces.TeamRepository;
import br.com.nba.api.services.interfaces.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl extends ServiceBaseImpl<Team, Integer> implements TeamService {
    @Autowired
    public TeamServiceImpl(@Qualifier("teamRepository") TeamRepository repository) {
        super(repository);
    }
}
