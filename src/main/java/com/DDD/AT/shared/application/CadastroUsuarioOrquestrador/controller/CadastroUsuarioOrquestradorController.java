package com.DDD.AT.shared.application.CadastroUsuarioOrquestrador.controller;

import com.DDD.AT.shared.application.CadastroUsuarioOrquestrador.service.CadastroUsuarioOrquestradorService;
import com.DDD.AT.usuario.dto.UsuarioRequest;
import com.DDD.AT.usuario.dto.UsuarioResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class CadastroUsuarioOrquestradorController {

    private final CadastroUsuarioOrquestradorService cadastroUsuarioOrquestradorService;

    public CadastroUsuarioOrquestradorController(CadastroUsuarioOrquestradorService cadastroUsuarioOrquestradorService) {
        this.cadastroUsuarioOrquestradorService = cadastroUsuarioOrquestradorService;
    }


    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioResponse> cadastrar(@RequestBody UsuarioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastroUsuarioOrquestradorService.cadastrarUsuarioComFavoritos(request));
    }
}