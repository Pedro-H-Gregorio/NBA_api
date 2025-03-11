package br.com.nba.api.entities.dtos.impl;

import br.com.nba.api.entities.User;
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
public class UserDTO implements DTO<User> {
    @NotNull(message = "Nome de usuário não pode ser nulo.")
    @NotBlank(message = "Nome de usuário não pode ser uma string vázia.")
    private String username;

    @NotNull(message = "Senha não pode ser nulo.")
    @NotBlank(message = "Senha não pode ser uma string vázia.")
    private String password;

    @Override
    public User toEntity() {
        return new User(username, password);
    }
}
