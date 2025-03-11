package br.com.nba.api.services.impl;

import br.com.nba.api.entities.PlayerSeason;
import br.com.nba.api.repositories.interfaces.PlayerSeasonRepository;
import br.com.nba.api.services.interfaces.PlayerSeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PlayerSeasonServiceImpl extends ServiceBaseImpl<PlayerSeason, String> implements PlayerSeasonService {

    private final PlayerSeasonRepository repository;

    @Autowired
    public PlayerSeasonServiceImpl(@Qualifier("playerSeasonRepository") PlayerSeasonRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Page<PlayerSeason> findAllPlayerBySeason(String seasonId, Pageable pageable) {
        return repository.findAllPlayerBySeason(seasonId, pageable);
    }
}
