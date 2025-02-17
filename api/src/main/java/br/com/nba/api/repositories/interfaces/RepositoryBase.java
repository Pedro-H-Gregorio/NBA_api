package br.com.nba.api.repositories.interfaces;

import br.com.nba.api.repositories.PersistenciaDawException;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RepositoryBase <E, T> {

    E save(E obj) throws PersistenciaDawException;

    E update(E obj) throws PersistenciaDawException;

    void delete(T primaryKey) throws PersistenciaDawException;

    E getByID(T primaryKey) throws PersistenciaDawException;

    List<E> getAll() throws PersistenciaDawException;
}