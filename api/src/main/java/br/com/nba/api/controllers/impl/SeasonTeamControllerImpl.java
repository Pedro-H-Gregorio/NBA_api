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

    //busca todos os times de uma temporada
    @GetMapping(path = {"/{id_season}/teams"})
    public ResponseEntity<List<SeasonTeam>> findAll(@PathVariable String id_season) throws PersistenciaDawException {
        List<SeasonTeam> all = ((SeasonTeamRepository) repository).getAllTeamsBySeason(id_season);
        return ResponseEntity.ok(all);
    }
}
