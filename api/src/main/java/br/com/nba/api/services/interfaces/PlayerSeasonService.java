package br.com.nba.api.services.interfaces;

import br.com.nba.api.entities.PlayerSeason;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PlayerSeasonService extends ServiceBase<PlayerSeason, String> {
    Page<PlayerSeason> findAllPlayerBySeason(String seasonId, Pageable pageable);
}
