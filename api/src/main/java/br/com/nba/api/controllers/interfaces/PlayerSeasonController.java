package br.com.nba.api.controllers.interfaces;

import br.com.nba.api.entities.PlayerSeason;
import br.com.nba.api.entities.dtos.impl.PlayerSeasonDTO;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Endpoint da Relação entre Jogadores e Temporadas", description = "Endpoint responsavel por fazer o CRUD de Relação entre Jogadores e Temporadas.")
public interface PlayerSeasonController extends ControllerBase<PlayerSeason, PlayerSeasonDTO, String> {
}
