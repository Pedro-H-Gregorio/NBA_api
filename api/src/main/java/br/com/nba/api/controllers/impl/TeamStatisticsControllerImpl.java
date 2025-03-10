package br.com.nba.api.controllers.impl;

import br.com.nba.api.controllers.interfaces.TeamStatisticsController;
import br.com.nba.api.entities.TeamStatistics;
import br.com.nba.api.entities.dtos.impl.TeamStatisticsDTO;
import br.com.nba.api.repositories.interfaces.TeamStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping({ "/team-statistics" })
public class TeamStatisticsControllerImpl extends ControllerBaseImpl<TeamStatistics, TeamStatisticsDTO, String>
        implements TeamStatisticsController {

    @Autowired
    protected TeamStatisticsControllerImpl(TeamStatisticsRepository repository) {
        super(repository);
    }
}
