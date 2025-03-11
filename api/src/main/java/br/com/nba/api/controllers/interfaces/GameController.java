package br.com.nba.api.controllers.interfaces;

import br.com.nba.api.entities.Game;
import br.com.nba.api.entities.dtos.impl.GameDTO;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Endpoint de Jogos", description = "Endpoint responsavel por fazer o CRUD de Jogos.")
public interface GameController extends ControllerBase<Game, GameDTO, String> {
}
