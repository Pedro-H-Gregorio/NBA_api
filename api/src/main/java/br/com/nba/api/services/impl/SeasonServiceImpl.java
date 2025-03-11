package br.com.nba.api.services.impl;

import br.com.nba.api.entities.Season;
import br.com.nba.api.repositories.interfaces.SeasonRepository;
import br.com.nba.api.services.interfaces.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class SeasonServiceImpl extends ServiceBaseImpl<Season, String> implements SeasonService {

    @Autowired
    public SeasonServiceImpl(@Qualifier("seasonRepository") SeasonRepository repository) {
        super(repository);
    }
}
