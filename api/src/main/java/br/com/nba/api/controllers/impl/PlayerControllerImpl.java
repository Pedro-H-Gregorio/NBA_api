package br.com.nba.api.controllers.impl;

import br.com.nba.api.controllers.interfaces.PlayerController;
import br.com.nba.api.entities.Player;
import br.com.nba.api.repositories.PersistenciaDawException;
import br.com.nba.api.repositories.interfaces.PlayerRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping({"/player"})
public class PlayerControllerImpl extends ControllerBaseImpl<Player, Integer> implements PlayerController {

    @Autowired
    protected PlayerControllerImpl(PlayerRepository repository) {
        super(repository);
    }
}