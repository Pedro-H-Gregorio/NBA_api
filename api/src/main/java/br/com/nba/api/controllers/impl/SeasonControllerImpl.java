package br.com.nba.api.controllers.impl;

import br.com.nba.api.controllers.interfaces.SeasonController;
import br.com.nba.api.entities.Season;
import br.com.nba.api.entities.dtos.impl.SeasonDTO;
import br.com.nba.api.services.interfaces.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping({ "/season" })
public class SeasonControllerImpl extends ControllerBaseImpl<Season, SeasonDTO, String> implements SeasonController {

    @Autowired
    protected SeasonControllerImpl(@Qualifier("seasonServiceImpl") SeasonService service) {
        super(service);
    }
}
