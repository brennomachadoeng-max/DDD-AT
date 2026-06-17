package com.DDD.AT.financeiro.cartaoCredito.controller;

import com.DDD.AT.financeiro.cartaoCredito.dto.CartaoCreditoRequest;
import com.DDD.AT.financeiro.cartaoCredito.dto.CartaoCreditoResponse;
import com.DDD.AT.financeiro.cartaoCredito.service.CartaoCreditoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/financeiro/cartoes")
public class CartaoCreditoController {

    private final CartaoCreditoService cartaoCreditoService;

    public CartaoCreditoController(CartaoCreditoService cartaoCreditoService) {
        this.cartaoCreditoService = cartaoCreditoService;
    }

    @PostMapping
    public ResponseEntity<CartaoCreditoResponse> cadastrar(@RequestBody CartaoCreditoRequest request) {
        CartaoCreditoResponse response = cartaoCreditoService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartaoCreditoResponse> buscarPorId(@PathVariable Long id) {
        CartaoCreditoResponse response = cartaoCreditoService.buscarPorId(id);
        if (response == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<CartaoCreditoResponse> ativar(@PathVariable Long id) {
        CartaoCreditoResponse response = cartaoCreditoService.ativar(id);
        if (response == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/desativar")
    public ResponseEntity<CartaoCreditoResponse> desativar(@PathVariable Long id) {
        CartaoCreditoResponse response = cartaoCreditoService.desativar(id);
        if (response == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }
}