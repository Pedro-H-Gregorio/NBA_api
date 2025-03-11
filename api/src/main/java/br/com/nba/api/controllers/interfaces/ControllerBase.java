package br.com.nba.api.controllers.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nba.api.entities.dtos.interfaces.DTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

public interface ControllerBase<E, D extends DTO<E>, T> {
    @Operation(summary = "Cria uma nova entidade", description = "Cria uma nova entidade baseada no DTO fornecido e retorna a entidade criada.", tags = {
            "Create" })
    ResponseEntity<E> create(@Valid @RequestBody D object);

    @Operation(summary = "Obtém uma entidade pelo ID", description = "Retorna uma entidade específica com base no ID fornecido.", tags = {
            "Read" })
    ResponseEntity<E> findById(@PathVariable("id") T id);

    @Operation(summary = "Atualiza uma entidade", description = "Atualiza a entidade existente com base no ID e no DTO fornecido.", tags = {
            "Update" })
    ResponseEntity<E> update(@PathVariable("id") T id, @Valid @RequestBody D object);

    @Operation(summary = "Deleta uma entidade", description = "Remove a entidade do sistema com base no ID fornecido.", tags = {
            "Delete" })
    ResponseEntity<Object> delete(@PathVariable("id") T id);

    @Operation(summary = "Obtém todas as entidades", description = "Retorna uma lista paginada com todas as entidades armazenadas no sistema.", tags = {
            "Read" })
    ResponseEntity<Page<E>> findAll(Pageable pageable);
}
