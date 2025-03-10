package br.com.nba.api.controllers.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nba.api.entities.dtos.interfaces.DTO;
import br.com.nba.api.repositories.PersistenciaDawException;
import jakarta.validation.Valid;

public interface ControllerBase<E, D extends DTO<E>, T> {
    ResponseEntity<E> create(@Valid @RequestBody D object) throws PersistenciaDawException;

    ResponseEntity<E> findById(@PathVariable("id") T id) throws PersistenciaDawException;

    ResponseEntity<E> update(@PathVariable("id") T id, @Valid @RequestBody D object)
            throws PersistenciaDawException;

    ResponseEntity<Object> delete(@PathVariable("id") T id) throws PersistenciaDawException;

    ResponseEntity<List<E>> findAll() throws PersistenciaDawException;
}
