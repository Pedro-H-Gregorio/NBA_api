package br.com.nba.api.controllers.impl;

import br.com.nba.api.controllers.interfaces.PlayerSeasonController;
import br.com.nba.api.entities.PlayerSeason;
import br.com.nba.api.entities.SeasonTeam;
import br.com.nba.api.entities.dtos.impl.PlayerSeasonDTO;
import br.com.nba.api.repositories.interfaces.PlayerSeasonRepository;
import br.com.nba.api.services.interfaces.PlayerSeasonService;
import br.com.nba.api.services.interfaces.SeasonTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping({ "/player_season" })
public class PlayerSeasonControllerImpl extends ControllerBaseImpl<PlayerSeason, PlayerSeasonDTO, String>
        implements PlayerSeasonController {

    @Autowired
    protected PlayerSeasonControllerImpl(@Qualifier("playerSeasonServiceImpl") PlayerSeasonService service) {
        super(service);
    }

    @GetMapping(path = { "/{idSeason}/players" })
    public ResponseEntity<Page<PlayerSeason>> findAll(@PathVariable String idSeason, @PageableDefault(size = 10) Pageable pageable) {
        Page<PlayerSeason> all = ((PlayerSeasonService) service).findAllPlayerBySeason(idSeason, pageable);
        return ResponseEntity.ok(all);
    }
}
