package br.com.nba.api.entities.dtos.impl;

import br.com.nba.api.entities.User;
import br.com.nba.api.entities.dtos.interfaces.DTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDTO implements DTO<User> {
    private String username;
    private String password;

    @Override
    public User toEntity() {
        return new User(username, password);
    }
}
