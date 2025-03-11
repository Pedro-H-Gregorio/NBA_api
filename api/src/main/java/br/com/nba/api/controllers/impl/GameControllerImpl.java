package br.com.nba.api.controllers.impl;

import br.com.nba.api.controllers.interfaces.GameController;
import br.com.nba.api.entities.Game;
import br.com.nba.api.entities.dtos.impl.GameDTO;
import br.com.nba.api.repositories.interfaces.GameRepository;
import br.com.nba.api.services.interfaces.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping({ "/game" })
public class GameControllerImpl extends ControllerBaseImpl<Game, GameDTO, String> implements GameController {

    @Autowired
    protected GameControllerImpl(@Qualifier("gameServiceImpl") GameService service) {
        super(service);
    }
}
