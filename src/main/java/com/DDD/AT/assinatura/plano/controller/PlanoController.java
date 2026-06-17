package com.DDD.AT.assinatura.plano.controller;

import com.DDD.AT.assinatura.plano.dto.PlanoRequest;
import com.DDD.AT.assinatura.plano.dto.PlanoResponse;
import com.DDD.AT.assinatura.plano.service.PlanoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assinaturas/planos")
public class PlanoController {

    private final PlanoService planoService;

    public PlanoController(PlanoService planoService) {
        this.planoService = planoService;
    }

    @PostMapping
    public ResponseEntity<PlanoResponse> criarPlano(@RequestBody PlanoRequest request) {
        PlanoResponse response = planoService.criarPlano(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PlanoResponse>> listarTodos() {
        List<PlanoResponse> planos = planoService.listarTodos();
        return ResponseEntity.ok(planos);
    }
}