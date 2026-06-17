package com.DDD.AT.usuario.controller;

import com.DDD.AT.usuario.dto.UsuarioRequest;
import com.DDD.AT.usuario.dto.UsuarioResponse;
import com.DDD.AT.usuario.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastrarUsuario(@RequestBody UsuarioRequest usuarioRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.criarConta(usuarioRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarConta(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscarConta(id));
    }
}
