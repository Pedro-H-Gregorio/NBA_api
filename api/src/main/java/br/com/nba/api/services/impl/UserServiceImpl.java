package br.com.nba.api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.nba.api.entities.UserDTO;
import br.com.nba.api.entities.User;
import br.com.nba.api.entities.UserDetailsImpl;
import br.com.nba.api.repositories.interfaces.UserRepository;
import br.com.nba.api.security.config.SecurityConfiguration;
import br.com.nba.api.services.interfaces.TokenService;
import br.com.nba.api.services.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService service;

    @Autowired
    private UserRepository repository;

    @Autowired
    private SecurityConfiguration configuration;

    public String authenticate(UserDTO dto) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto.username(),
                dto.password());
        Authentication authentication = manager.authenticate(token);
        UserDetailsImpl details = (UserDetailsImpl) authentication.getPrincipal();
        return service.generateToken(details.getUser());
    }

    public void register(UserDTO dto) {
        String encodedPassword = configuration.passwordEncoder().encode(dto.password());
        User user = new User(dto.username(), encodedPassword);
        repository.save(user);
    }
}
