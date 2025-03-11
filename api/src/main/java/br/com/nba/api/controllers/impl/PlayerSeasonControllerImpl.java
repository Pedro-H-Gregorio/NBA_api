package br.com.nba.api.controllers.impl;

import br.com.nba.api.controllers.interfaces.PlayerSeasonController;
import br.com.nba.api.entities.PlayerSeason;
import br.com.nba.api.repositories.PersistenciaDawException;
import br.com.nba.api.repositories.interfaces.PlayerSeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping({"/player_season"})
public class PlayerSeasonControllerImpl extends ControllerBaseImpl<PlayerSeason, String> implements PlayerSeasonController {

    @Autowired
    protected PlayerSeasonControllerImpl(PlayerSeasonRepository repository) {
        super(repository);
    }

    //busca todos os jogadores de uma temporada
    @GetMapping(path = {"/{id_season}/players"})
    public ResponseEntity<List<PlayerSeason>> findAll(@PathVariable String id_season) throws PersistenciaDawException {
        List<PlayerSeason> all = ((PlayerSeasonRepository) repository).getAllPlayersBySeason(id_season);
        return ResponseEntity.ok(all);
    }
}