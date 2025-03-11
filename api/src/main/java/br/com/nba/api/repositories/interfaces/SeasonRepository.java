package br.com.nba.api.repositories.interfaces;

import br.com.nba.api.entities.Season;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface SeasonRepository extends RepositoryBase<Season, String>{
}
