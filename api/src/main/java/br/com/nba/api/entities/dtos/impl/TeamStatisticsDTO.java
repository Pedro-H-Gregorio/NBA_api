package br.com.nba.api.entities.dtos.impl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import br.com.nba.api.entities.Game;
import br.com.nba.api.entities.Team;
import br.com.nba.api.entities.TeamStatistics;
import br.com.nba.api.entities.dtos.interfaces.DTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeamStatisticsDTO implements DTO<TeamStatistics> {
    @NotNull(message = "Id não pode ser nulo.")
    @NotBlank(message = "Id não pode ser uma string vázia.")
    private String id;

    @NotBlank(message = "WL não pode ser uma string vázia.")
    @Size(message = "WL não pode ter tamanho maior que 50.", max = 50)
    private String wl;

    @NotNull(message = "Pontos não pode ser nulo.")
    private Integer points;

    @NotNull(message = "Rebotes não pode ser nulo.")
    private Integer rebounds;

    @NotNull(message = "Bloqueios não pode ser nulo.")
    private Integer blocks;

    @NotNull(message = "Assistências não pode ser nulo.")
    private Integer assistances;

    @NotNull(message = "Roubos não pode ser nulo.")
    private Integer steals;

    @NotNull(message = "Arremessos de três pontos não pode ser nulo.")
    private Integer threePointShots;

    @NotNull(message = "Baskets de perímetro não pode ser nulo.")
    private Integer perimeterBaskets;

    @NotNull(message = "Lançamentos livres não pode ser nulo.")
    private Integer freeThrows;

    @NotNull(message = "Time não pode ser nulo.")
    private Team team;

    @NotNull(message = "Jogo não pode ser nulo.")
    private Game game;

    @Override
    public TeamStatistics toEntity() {
        return new TeamStatistics(id, wl, points, rebounds, blocks, assistances, steals,
                threePointShots, perimeterBaskets, freeThrows, team, game);
    }
}
