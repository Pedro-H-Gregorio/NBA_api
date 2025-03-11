package br.com.nba.api.entities.dtos.impl;

import java.util.List;

import br.com.nba.api.entities.Player;
import br.com.nba.api.entities.PlayerStatistics;
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
public class PlayerDTO implements DTO<Player> {
    @NotNull(message = "Id não pode ser nulo.")
    private Integer id;

    @NotBlank(message = "Nome não pode ser uma string vázia.")
    private String name;

    @NotBlank(message = "Apelido não pode ser uma string vázia.")
    private String nickName;

    @NotBlank(message = "Número da camisa não pode ser uma string vázia.")
    private String shirtNumber;

    @NotBlank(message = "Posição não pode ser uma string vázia.")
    private String position;

    @NotBlank(message = "Altura não pode ser uma string vázia.")
    private String height;

    @NotBlank(message = "Peso não pode ser uma string vázia.")
    private String weight;

    @NotBlank(message = "Ano de nascimento não pode ser uma string vázia.")
    private String birthDate;

    private Integer age;

    private Team team;

    private List<PlayerStatistics> statistics;

    @Override
    public Player toEntity() {
        return new Player(id, name, nickName, shirtNumber, position, height, weight, birthDate, age, team, statistics);
    }
}
