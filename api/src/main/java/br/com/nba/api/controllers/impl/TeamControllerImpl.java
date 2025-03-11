package br.com.nba.api.controllers.impl;

import br.com.nba.api.controllers.interfaces.TeamController;
import br.com.nba.api.entities.Team;
import br.com.nba.api.entities.dtos.impl.TeamDTO;
import br.com.nba.api.repositories.interfaces.TeamRepository;
import br.com.nba.api.services.interfaces.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping({ "/team" })
public class TeamControllerImpl extends ControllerBaseImpl<Team, TeamDTO, Integer> implements TeamController {

    @Autowired
    protected TeamControllerImpl(@Qualifier("teamServiceImpl") TeamService service) {
        super(service);
    }
}
