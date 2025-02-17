package br.com.nba.api.repositories.impl;

import br.com.nba.api.entities.Player;
import br.com.nba.api.repositories.interfaces.PlayerRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerRepositoryImpl extends RepositoryBaseImpl<Player, Integer> implements PlayerRepository {
    public PlayerRepositoryImpl() {
        super(Player.class);
    }

    @Override
    protected String getEntityName() {
        return "PLAYER";
    }
}
