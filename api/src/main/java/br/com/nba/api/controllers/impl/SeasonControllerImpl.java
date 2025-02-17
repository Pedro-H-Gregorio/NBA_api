package br.com.nba.api.controllers.impl;

import br.com.nba.api.controllers.interfaces.SeasonController;
import br.com.nba.api.entities.Season;
import br.com.nba.api.repositories.interfaces.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping({"/season"})
public class SeasonControllerImpl extends ControllerBaseImpl<Season, String> implements SeasonController {

    @Autowired
    protected SeasonControllerImpl(SeasonRepository repository) {
        super(repository);
    }
}
