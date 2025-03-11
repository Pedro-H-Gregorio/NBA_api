package br.com.nba.api.controllers.interfaces;

import br.com.nba.api.entities.TeamStatistics;
import br.com.nba.api.entities.dtos.impl.TeamStatisticsDTO;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Endpoint da Estatísticas de Times", description = "Endpoint responsavel por fazer o CRUD de Estatísticas de Times.")
public interface TeamStatisticsController extends ControllerBase<TeamStatistics, TeamStatisticsDTO, String> {
}
