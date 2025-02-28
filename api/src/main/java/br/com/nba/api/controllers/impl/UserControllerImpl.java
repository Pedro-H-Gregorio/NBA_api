package br.com.nba.api.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nba.api.entities.UserDTO;
import br.com.nba.api.services.interfaces.UserService;

@RestController
@RequestMapping("/auth")
public class UserControllerImpl {
    @Autowired
    private UserService service;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO dto) {
        String token = service.authenticate(dto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserDTO dto) {
        service.register(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
