package br.com.nba.repositories.interfaces;

import br.com.nba.repositories.PersistenciaDawException;

import java.util.List;

public interface RepositoryBase <E, T> {

    void save(E obj) throws PersistenciaDawException;

    E update(E obj) throws PersistenciaDawException;

    void delete(T primaryKey) throws PersistenciaDawException;

    E getByID(T primaryKey) throws PersistenciaDawException;

    List<E> getAll() throws PersistenciaDawException;
}