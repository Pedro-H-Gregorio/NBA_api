package br.com.nba.api.controllers.interfaces;

import br.com.nba.api.entities.SeasonTeam;
import br.com.nba.api.entities.dtos.impl.SeasonTeamDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface SeasonTeamController extends ControllerBase<SeasonTeam, SeasonTeamDTO, String> {
    ResponseEntity<Page<SeasonTeam>> findAll(@PathVariable String idSeason, @PageableDefault(size = 10) Pageable pageable);
}
