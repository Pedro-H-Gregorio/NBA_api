package br.com.nba.api.controllers.interfaces;

import br.com.nba.api.entities.SeasonTeam;
import br.com.nba.api.entities.dtos.impl.SeasonTeamDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Endpoint da Relação entre Temporadas e Times", description = "Endpoint responsavel por fazer o CRUD de Relação entre Temporadas e Times.")
public interface SeasonTeamController extends ControllerBase<SeasonTeam, SeasonTeamDTO, String> {
    @Operation(summary = "Obtém todas as relações entre Temporadas e Times", description = "Retorna uma lista paginada com todas as relações de Temporadas e Times armazenadas no sistema, com a possibilidade de paginação.", tags = {
            "Read" })
    ResponseEntity<Page<SeasonTeam>> findAll(@PathVariable String idSeason,
            @PageableDefault(size = 10) Pageable pageable);
}
