package br.com.nba.api.repositories.interfaces;

import br.com.nba.api.entities.SeasonTeam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonTeamRepository extends RepositoryBase<SeasonTeam, String> {
    @Query("SELECT seasonTeam from SEASON_TEAM seasonTeam where ( seasonTeam.season.id = :seasonId)")
    Page<SeasonTeam> getAllTeamsBySeason(String seasonId, Pageable pageable);
}
