package br.com.nba.api.controllers.impl;

import br.com.nba.api.controllers.interfaces.SeasonTeamController;
import br.com.nba.api.entities.SeasonTeam;
import br.com.nba.api.entities.dtos.impl.SeasonTeamDTO;
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
@RequestMapping({ "/season-teams" })
public class SeasonTeamControllerImpl extends ControllerBaseImpl<SeasonTeam, SeasonTeamDTO, String>
        implements SeasonTeamController {

    @Autowired
    protected SeasonTeamControllerImpl(@Qualifier("seasonTeamServiceImpl") SeasonTeamService service) {
        super(service);
    }

    @GetMapping(path = { "/{idSeason}/teams" })
    public ResponseEntity<Page<SeasonTeam>> findAll(@PathVariable String idSeason,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<SeasonTeam> all = ((SeasonTeamService) service).getAllTeamsBySeason(idSeason, pageable);
        return ResponseEntity.ok(all);
    }
}
