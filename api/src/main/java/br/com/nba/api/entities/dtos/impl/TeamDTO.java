package br.com.nba.api.entities.dtos.impl;

import java.util.List;

import br.com.nba.api.entities.Team;
import br.com.nba.api.entities.TeamStatistics;
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
public class TeamDTO implements DTO<Team> {
    @NotNull(message = "Id não pode ser nulo.")
    private Integer id;

    @NotNull(message = "Cidade não pode ser nula.")
    @NotBlank(message = "Cidade não pode ser uma string vázia.")
    private String city;

    @NotNull(message = "Time não pode ser nulo.")
    @NotBlank(message = "Time não pode ser uma string vázia.")
    private String nickname;

    @NotNull(message = "Abreviação não pode ser nula.")
    @NotBlank(message = "Abreviação não pode ser uma string vázia.")
    private String abbreviation;

    @NotNull(message = "Nome completo não pode ser nulo.")
    @NotBlank(message = "Nome completo pode ser uma string vázia.")
    private String fullName;

    @NotNull(message = "Ano fundado não pode ser nulo.")
    private Integer yearFounded;

    @NotNull(message = "Estado não pode ser nulo.")
    @NotBlank(message = "Estado não pode ser uma string vázia.")
    private String state;

    List<TeamStatistics> statistics;

    @Override
    public Team toEntity() {
        return new Team(id, city, nickname, abbreviation, fullName, yearFounded, state, statistics);
    }
}
