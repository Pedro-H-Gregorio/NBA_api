package br.com.nba.api.services.interfaces;

import br.com.nba.api.entities.SeasonTeam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SeasonTeamService extends ServiceBase<SeasonTeam, String> {
    Page<SeasonTeam> getAllTeamsBySeason(String seasonId, Pageable pageable);
}
