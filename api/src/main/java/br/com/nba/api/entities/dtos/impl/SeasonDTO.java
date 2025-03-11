package br.com.nba.api.entities.dtos.impl;

import br.com.nba.api.entities.Season;
import br.com.nba.api.entities.dtos.interfaces.DTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SeasonDTO implements DTO<Season> {
    @NotNull(message = "Id não pode ser nulo.")
    @NotBlank(message = "Id não pode ser uma string vázia.")
    private String id;

    @NotNull(message = "Ano não pode ser nula.")
    @NotBlank(message = "Ano não pode ser uma string vázia.")
    @Size(message = "Ano não pode ter tamanho maior que 7.", max = 7)
    private String year;

    @Override
    public Season toEntity() {
        return new Season(id, year);
    }
}
