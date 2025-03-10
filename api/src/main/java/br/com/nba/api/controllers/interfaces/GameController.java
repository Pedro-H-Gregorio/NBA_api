package br.com.nba.api.controllers.interfaces;

import br.com.nba.api.entities.Game;
import br.com.nba.api.entities.dtos.impl.GameDTO;

public interface GameController extends ControllerBase<Game, GameDTO, String> {
}
