package br.com.nba.api.services.impl;

import br.com.nba.api.repositories.interfaces.RepositoryBase;
import br.com.nba.api.services.interfaces.ServiceBase;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


public abstract class ServiceBaseImpl<E,T> implements ServiceBase<E,T> {

    private final RepositoryBase<E, T> repository;


    public ServiceBaseImpl(RepositoryBase<E,T> repository) {
        this.repository = repository;
    }


    public E save(E entity) {
        return repository.save(entity);
    }

    public E update(E entity) {
        return repository.save(entity);
    }

    public Page<E> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<E> getById(T id) {
        return repository.findById(id);
    }

    public void delete(T id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Entidade não encontrada para o ID: " + id);
        }
    }
}
