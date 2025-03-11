package br.com.nba.api.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public interface ServiceBase<E,T> {
    E save(E entity);
    E update(E entity);
    Page<E> getAll(Specification<E> specification, Pageable pageable);
    void delete(T entity);
    Optional<E> getById(T id);
}
