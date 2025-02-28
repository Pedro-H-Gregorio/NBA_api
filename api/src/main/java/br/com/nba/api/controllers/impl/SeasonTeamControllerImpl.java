package br.com.nba.api.controllers.impl;

import br.com.nba.api.controllers.interfaces.SeasonTeamController;
import br.com.nba.api.entities.SeasonTeam;
import br.com.nba.api.repositories.PersistenciaDawException;
import br.com.nba.api.repositories.interfaces.SeasonTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping({"/season-teams"})
public class SeasonTeamControllerImpl extends ControllerBaseImpl<SeasonTeam, String> implements SeasonTeamController {

    @Autowired
    protected SeasonTeamControllerImpl(SeasonTeamRepository repository) {
        super(repository);
    }


    @GetMapping(path = {"/{idSeason}/teams"})
    public ResponseEntity<List<SeasonTeam>> findAll(@PathVariable String idSeason) throws PersistenciaDawException {
        List<SeasonTeam> all = ((SeasonTeamRepository) repository).getAllTeamsBySeason(idSeason);
        return ResponseEntity.ok(all);
    }
}
