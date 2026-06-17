package com.DDD.AT.assinatura.controller;

import com.DDD.AT.assinatura.dto.AssinaturaRequest;
import com.DDD.AT.assinatura.dto.AssinaturaResponse;
import com.DDD.AT.assinatura.service.AssinaturaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assinaturas")
public class AssinaturaController {

    private final AssinaturaService assinaturaService;

    public AssinaturaController(AssinaturaService assinaturaService) {
        this.assinaturaService = assinaturaService;
    }

    @PostMapping
    public ResponseEntity<AssinaturaResponse> criarAssinatura(@RequestBody AssinaturaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(assinaturaService.criarAssinatura(request));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<AssinaturaResponse>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(assinaturaService.listarPorUsuario(usuarioId));
    }
}