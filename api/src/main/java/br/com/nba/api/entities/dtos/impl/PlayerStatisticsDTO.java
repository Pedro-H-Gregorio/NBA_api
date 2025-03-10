package br.com.nba.api.entities.dtos.impl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import br.com.nba.api.entities.Game;
import br.com.nba.api.entities.Player;
import br.com.nba.api.entities.PlayerStatistics;
import br.com.nba.api.entities.dtos.interfaces.DTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlayerStatisticsDTO implements DTO<PlayerStatistics> {
    @NotNull(message = "Id não pode ser nulo.")
    @NotBlank(message = "Id não pode ser uma string vázia.")
    private String id;

    private Integer points;

    private Integer rebounds;

    private Integer blocks;

    private Integer assistances;

    private Integer steals;

    private Integer threePointShots;

    private Integer perimeterBaskets;

    private Integer freeThrows;

    private Player player;

    private Game game;

    @Override
    public PlayerStatistics toEntity() {
        return new PlayerStatistics(id, points, rebounds, blocks, assistances, steals, threePointShots,
                perimeterBaskets, freeThrows, player, game);
    }
}
