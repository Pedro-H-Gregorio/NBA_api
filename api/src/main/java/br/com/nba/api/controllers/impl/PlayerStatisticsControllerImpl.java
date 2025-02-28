package br.com.nba.api.controllers.impl;

import br.com.nba.api.controllers.interfaces.PlayerStatisticsController;
import br.com.nba.api.entities.PlayerStatistics;
import br.com.nba.api.repositories.interfaces.PlayerStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping({"/player_statistics"})
public class PlayerStaticsControllerImpl extends ControllerBaseImpl<PlayerStatistics, String> implements PlayerStatisticsController {

    @Autowired
    protected PlayerStaticsControllerImpl(PlayerStatisticsRepository repository) {
        super(repository);
    }
}

