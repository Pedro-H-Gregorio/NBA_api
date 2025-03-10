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

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) throws PersistenciaDawException {
        Optional<Player> savedPlayer = Optional.ofNullable(repository.save(player));
        return savedPlayer.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> findPlayerById(@PathVariable("id") Integer id) throws PersistenciaDawException {
        Optional<Player> player = Optional.ofNullable(repository.getByID(id));
        return player.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable("id") Integer id, @RequestBody Player player) throws PersistenciaDawException {
        Optional<Player> updatedPlayer = Optional.ofNullable(repository.update(player));
        return updatedPlayer.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePlayer(@PathVariable("id") Integer id) throws PersistenciaDawException {
        Optional<Player> player = Optional.ofNullable(repository.getByID(id));
        return player.map(p -> {
            try {
                repository.delete(id);
            } catch (PersistenciaDawException e) {
                e.printStackTrace();
            }
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Player>> findAllPlayers() throws PersistenciaDawException {
        List<Player> players = repository.getAll();
        return ResponseEntity.ok(players);
    }
}