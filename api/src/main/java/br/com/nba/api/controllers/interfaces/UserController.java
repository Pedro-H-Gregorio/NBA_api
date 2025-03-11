package br.com.nba.api.controllers.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.nba.api.entities.dtos.impl.UserDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Endpoint de Usuários", description = "Endpoint responsavel por fazer o registro e login de Usuários.")
public interface UserController {
    public ResponseEntity<String> login(@RequestBody @Valid UserDTO dto);

    public ResponseEntity<Void> register(@RequestBody @Valid UserDTO dto);
}
