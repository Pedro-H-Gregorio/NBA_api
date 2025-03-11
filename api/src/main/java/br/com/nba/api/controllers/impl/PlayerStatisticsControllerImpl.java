package br.com.nba.api.controllers.impl;

import br.com.nba.api.controllers.interfaces.PlayerStatisticsController;
import br.com.nba.api.entities.PlayerStatistics;
import br.com.nba.api.entities.dtos.impl.PlayerStatisticsDTO;
import br.com.nba.api.services.interfaces.PlayerStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping({ "/player_statistics" })
public class PlayerStatisticsControllerImpl extends ControllerBaseImpl<PlayerStatistics, PlayerStatisticsDTO, String>
        implements PlayerStatisticsController {

    @Autowired
    protected PlayerStatisticsControllerImpl(
            @Qualifier("playerStatisticsServiceImpl") PlayerStatisticsService service) {
        super(service);
    }
}
