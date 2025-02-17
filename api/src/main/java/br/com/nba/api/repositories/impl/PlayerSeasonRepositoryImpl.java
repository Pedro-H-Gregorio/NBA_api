package br.com.nba.api.repositories.impl;

import br.com.nba.api.entities.PlayerSeason;
import br.com.nba.api.repositories.interfaces.PlayerSeasonRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerSeasonRepositoryImpl extends RepositoryBaseImpl<PlayerSeason, String> implements PlayerSeasonRepository {
    public PlayerSeasonRepositoryImpl() {
        super(PlayerSeason.class);
    }

    @Override
    protected String getEntityName() {
        return "PLAYER_SEASON";
    }
}
