package br.com.nba.api.controllers.interfaces;

import br.com.nba.api.entities.Player;
import br.com.nba.api.entities.dtos.impl.PlayerDTO;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Endpoint de Jogadores", description = "Endpoint responsavel por fazer o CRUD de Jogadores.")
public interface PlayerController extends ControllerBase<Player, PlayerDTO, Integer> {
}
