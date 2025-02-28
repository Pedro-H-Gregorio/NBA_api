package br.com.nba.api.services.interfaces;

import br.com.nba.api.entities.UserDTO;

public interface UserService {
    String authenticate(UserDTO dto);

    void register(UserDTO dto);
}
