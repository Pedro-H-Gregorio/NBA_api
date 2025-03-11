package br.com.nba.api.controllers.impl;

import br.com.nba.api.controllers.interfaces.PlayerController;
import br.com.nba.api.entities.Player;
import br.com.nba.api.entities.dtos.impl.PlayerDTO;
import br.com.nba.api.services.interfaces.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping({ "/player" })
public class PlayerControllerImpl extends ControllerBaseImpl<Player, PlayerDTO, Integer> implements PlayerController {

    @Autowired
    protected PlayerControllerImpl(@Qualifier("playerServiceImpl") PlayerService service) {
        super(service);
    }
}
