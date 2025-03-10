package br.com.nba.api.controllers.impl;

import br.com.nba.api.controllers.interfaces.ControllerBase;
import br.com.nba.api.entities.dtos.interfaces.DTO;
import br.com.nba.api.repositories.PersistenciaDawException;
import br.com.nba.api.repositories.interfaces.RepositoryBase;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class ControllerBaseImpl<E, D extends DTO<E>, T> implements ControllerBase<E, D, T> {
    protected RepositoryBase<E, T> repository;

    protected ControllerBaseImpl(RepositoryBase<E, T> repository) {
        this.repository = repository;
    }

    @Override
    @PostMapping
    public ResponseEntity<E> create(@Valid @RequestBody D object) throws PersistenciaDawException {
        Optional<E> savedEntity = Optional.ofNullable(repository.save(object.toEntity()));
        return savedEntity.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Override
    @GetMapping(path = { "/{id}" })
    public ResponseEntity<E> findById(@PathVariable("id") T id) throws PersistenciaDawException {
        Optional<E> entity = Optional.ofNullable(repository.getByID(id));
        return entity.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity<E> update(@PathVariable("id") T id, @Valid @RequestBody D object)
            throws PersistenciaDawException {
        Optional<E> updatedEntity = Optional.ofNullable(repository.update(object.toEntity()));
        return updatedEntity.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Override
    @DeleteMapping(path = { "/{id}" })
    public ResponseEntity<Object> delete(@PathVariable("id") T id) throws PersistenciaDawException {
        Optional<E> entity = Optional.ofNullable(repository.getByID(id));
        return entity.map(object -> {
            try {
                repository.delete(id);
                return ResponseEntity.ok().build();
            } catch (PersistenciaDawException e) {
                throw new RuntimeException(e);
            }
        }).orElse(ResponseEntity.notFound().build());
    }

    @Override
    @GetMapping
    public ResponseEntity<List<E>> findAll() throws PersistenciaDawException {
        List<E> all = repository.getAll();
        return ResponseEntity.ok(all);
    }
}
