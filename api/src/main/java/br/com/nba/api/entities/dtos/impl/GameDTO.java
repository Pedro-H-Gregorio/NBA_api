package br.com.nba.api.entities.dtos.impl;

import br.com.nba.api.entities.Game;
import br.com.nba.api.entities.Season;
import br.com.nba.api.entities.Team;
import br.com.nba.api.entities.dtos.interfaces.DTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GameDTO implements DTO<Game> {
    @NotNull(message = "Id não pode ser nulo.")
    @NotBlank(message = "Id não pode ser uma string vázia.")
    private String id;

    @NotNull(message = "Matchup não pode ser nula.")
    @NotBlank(message = "Id não pode ser uma string vázia.")
    private String matchup;

    @NotNull(message = "Temporada não pode ser nula.")
    private Season season;

    @NotNull(message = "Time de casa não pode ser nulo.")
    private Team homeTeam;

    @NotNull(message = "Time visitante não pode ser nulo.")
    private Team awayTeam;

    private Team winnerTeam;

    @Override
    public Game toEntity() {
        return new Game(id, matchup, season, homeTeam, awayTeam, winnerTeam);
    }
}
