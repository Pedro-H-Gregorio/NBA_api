package br.com.nba.api.entities.dtos.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import br.com.nba.api.entities.Season;
import br.com.nba.api.entities.SeasonTeam;
import br.com.nba.api.entities.Team;
import br.com.nba.api.entities.dtos.interfaces.DTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SeasonTeamDTO implements DTO<SeasonTeam> {
    @NotNull(message = "Id não pode ser nulo.")
    @NotBlank(message = "Id não pode ser uma string vázia.")
    private String id;

    @NotNull(message = "Temporada não pode ser nula.")
    private Season season;

    @NotNull(message = "Time não pode ser nulo.")
    private Team team;

    @Override
    public SeasonTeam toEntity() {
        return new SeasonTeam(id, season, team);
    }
}
