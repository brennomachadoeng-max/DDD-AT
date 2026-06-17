package com.DDD.AT.financeiro.transacao.controller;

import com.DDD.AT.financeiro.transacao.dto.TransacaoRequest;
import com.DDD.AT.financeiro.transacao.dto.TransacaoResponse;
import com.DDD.AT.financeiro.transacao.service.TransacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/financeiro/transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<TransacaoResponse> autorizarPagamento(@RequestBody TransacaoRequest request) {
        TransacaoResponse response = transacaoService.processarPagamento(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}