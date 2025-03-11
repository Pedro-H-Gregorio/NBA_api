package br.com.nba.api.controllers.impl;

import br.com.nba.api.controllers.interfaces.ControllerBase;
import br.com.nba.api.entities.dtos.interfaces.DTO;
import br.com.nba.api.services.interfaces.ServiceBase;
import br.com.nba.api.services.specification.GenericSpecification;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

public class ControllerBaseImpl<E, D extends DTO<E>, T> implements ControllerBase<E, D, T> {
    protected ServiceBase<E, T> service;

    protected ControllerBaseImpl(ServiceBase<E, T> service) {
        this.service = service;
    }

    @Override
    @PostMapping
    public ResponseEntity<E> create(@Valid @RequestBody D object) {
        Optional<E> savedEntity = Optional.ofNullable(service.save(object.toEntity()));
        return savedEntity.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Override
    @GetMapping(path = { "/{id}" })
    public ResponseEntity<E> findById(@PathVariable("id") T id) {
        Optional<E> entity = service.getById(id);
        return entity.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity<E> update(@PathVariable("id") T id, @Valid @RequestBody D object) {
        Optional<E> updatedEntity = Optional.ofNullable(service.update(object.toEntity()));
        return updatedEntity.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Override
    @DeleteMapping(path = { "/{id}" })
    public ResponseEntity<Object> delete(@PathVariable("id") T id) {
        Optional<E> entity = service.getById(id);
        return entity.map(object -> {
            service.delete(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<E>> findAll(@RequestParam Map<String, Object> filters, @PageableDefault(size = 10) Pageable pageable) {
        Specification<E> spec = new GenericSpecification<>(filters);
        Page<E> all = service.getAll(spec, pageable);
        return ResponseEntity.ok(all);
    }
}
