package br.com.nba.api.controllers.interfaces;

import br.com.nba.api.entities.PlayerStatistics;
import br.com.nba.api.entities.dtos.impl.PlayerStatisticsDTO;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Endpoint da Estatísticas de Jogadores", description = "Endpoint responsavel por fazer o CRUD de Estatísticas de Jogadores.")
public interface PlayerStatisticsController extends ControllerBase<PlayerStatistics, PlayerStatisticsDTO, String> {
}
