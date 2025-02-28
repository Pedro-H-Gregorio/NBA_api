package br.com.nba.api.services.interfaces;

import br.com.nba.api.entities.User;

public interface TokenService {
    String generateToken(User user);

    String getSubjectFromToken(String token);
}
