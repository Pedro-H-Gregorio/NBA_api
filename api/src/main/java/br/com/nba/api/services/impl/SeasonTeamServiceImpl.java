package br.com.nba.api.services.impl;

import br.com.nba.api.entities.SeasonTeam;
import br.com.nba.api.repositories.interfaces.SeasonTeamRepository;
import br.com.nba.api.services.interfaces.SeasonTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeasonTeamServiceImpl extends ServiceBaseImpl<SeasonTeam, String> implements SeasonTeamService {

    private final SeasonTeamRepository seasonTeamRepository;

    @Autowired
    public SeasonTeamServiceImpl(@Qualifier("seasonTeamRepository") SeasonTeamRepository repository) {
        super(repository);
        this.seasonTeamRepository = repository;
    }

    @Override
    public Page<SeasonTeam> getAllTeamsBySeason(String seasonId, Pageable pageable) {
        return seasonTeamRepository.getAllTeamsBySeason(seasonId, pageable);
    }
}
