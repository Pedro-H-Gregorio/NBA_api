package br.com.nba.api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.nba.api.entities.dtos.impl.UserDTO;
import br.com.nba.api.entities.UserDetailsImpl;
import br.com.nba.api.repositories.interfaces.UserRepository;
import br.com.nba.api.config.security.SecurityConfiguration;
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
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto.getUsername(),
                dto.getPassword());
        Authentication authentication = manager.authenticate(token);
        UserDetailsImpl details = (UserDetailsImpl) authentication.getPrincipal();
        return service.generateToken(details.getUser());
    }

    public void register(UserDTO dto) {
        dto.setPassword(configuration.passwordEncoder().encode(dto.getPassword()));
        repository.save(dto.toEntity());
    }
}
