package br.com.nba.api.entities.dtos.impl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import br.com.nba.api.entities.Player;
import br.com.nba.api.entities.PlayerSeason;
import br.com.nba.api.entities.Season;
import br.com.nba.api.entities.dtos.interfaces.DTO;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PlayerSeasonDTO implements DTO<PlayerSeason> {
    @NotNull(message = "Id não pode ser nulo.")
    @NotBlank(message = "Id não pode ser uma string vázia.")
    private String id;

    @NotNull(message = "Temporada não pode ser nulo.")
    private Season season;

    @NotNull(message = "Jogador não pode ser nulo.")
    private Player player;

    @Override
    public PlayerSeason toEntity() {
        return new PlayerSeason(id, season, player);
    }
}
