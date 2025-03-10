package br.com.nba.api.services.interfaces;

import br.com.nba.api.entities.dtos.impl.UserDTO;

public interface UserService {
    String authenticate(UserDTO dto);

    void register(UserDTO dto);
}
