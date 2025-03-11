package br.com.nba.api.controllers.interfaces;

import br.com.nba.api.entities.Season;
import br.com.nba.api.entities.dtos.impl.SeasonDTO;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Endpoint de Temporadas", description = "Endpoint responsavel por fazer o CRUD de Temporadas.")
public interface SeasonController extends ControllerBase<Season, SeasonDTO, String> {
}
