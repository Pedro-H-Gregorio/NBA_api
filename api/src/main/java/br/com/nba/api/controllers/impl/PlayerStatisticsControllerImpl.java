package br.com.nba.api.controllers.impl;

import br.com.nba.api.controllers.interfaces.PlayerStatisticsController;
import br.com.nba.api.entities.PlayerStatistics;
import br.com.nba.api.entities.dtos.impl.PlayerStatisticsDTO;
import br.com.nba.api.repositories.interfaces.PlayerStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping({ "/player_statistics" })
public class PlayerStatisticsControllerImpl extends ControllerBaseImpl<PlayerStatistics, PlayerStatisticsDTO, String>
        implements PlayerStatisticsController {

    @Autowired
    protected PlayerStatisticsControllerImpl(PlayerStatisticsRepository repository) {
        super(repository);
    }
}
