package br.com.nba.api.repositories.interfaces;

import br.com.nba.api.entities.PlayerSeason;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerSeasonRepository extends RepositoryBase<PlayerSeason, String>{
    @Query("SELECT ps from PLAYER_SEASON ps where ( ps.season.id = :seasonId)")
    Page<PlayerSeason> findAllPlayerBySeason(String seasonId, Pageable pageable);
}
