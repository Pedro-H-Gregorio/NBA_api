package br.com.nba.api.controllers.interfaces;

import br.com.nba.api.repositories.PersistenciaDawException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ControllerBase<E,T> {
    ResponseEntity<E> create(@RequestBody E object) throws PersistenciaDawException;
    ResponseEntity<E> findById(@PathVariable("id") T id) throws PersistenciaDawException;
    ResponseEntity<E> update(@PathVariable("id") T id, @RequestBody E object) throws PersistenciaDawException;
    ResponseEntity<Object> delete(@PathVariable("id") T id) throws PersistenciaDawException;
    ResponseEntity<List<E>> findAll() throws PersistenciaDawException;
}
