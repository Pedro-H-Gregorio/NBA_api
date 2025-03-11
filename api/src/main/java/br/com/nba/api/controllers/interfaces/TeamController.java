package br.com.nba.api.controllers.interfaces;

import br.com.nba.api.entities.Team;
import br.com.nba.api.entities.dtos.impl.TeamDTO;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Endpoint de Times", description = "Endpoint responsavel por fazer o CRUD de Times.")
public interface TeamController extends ControllerBase<Team, TeamDTO, Integer> {
}
