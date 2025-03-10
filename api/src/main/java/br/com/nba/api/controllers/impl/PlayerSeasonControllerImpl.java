package br.com.nba.api.controllers.impl;

import br.com.nba.api.controllers.interfaces.PlayerSeasonController;
import br.com.nba.api.entities.PlayerSeason;
import br.com.nba.api.entities.dtos.impl.PlayerSeasonDTO;
import br.com.nba.api.repositories.interfaces.PlayerSeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping({ "/player_season" })
public class PlayerSeasonControllerImpl extends ControllerBaseImpl<PlayerSeason, PlayerSeasonDTO, String>
        implements PlayerSeasonController {

    @Autowired
    protected PlayerSeasonControllerImpl(PlayerSeasonRepository repository) {
        super(repository);
    }
}
