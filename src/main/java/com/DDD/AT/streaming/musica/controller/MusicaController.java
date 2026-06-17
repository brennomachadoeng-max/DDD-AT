package com.DDD.AT.streaming.musica.controller;

import com.DDD.AT.streaming.musica.dto.MusicaRequest;
import com.DDD.AT.streaming.musica.dto.MusicaResponse;
import com.DDD.AT.streaming.musica.service.MusicaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/streaming/musicas")
public class MusicaController {

    private final MusicaService musicaService;

    public MusicaController(MusicaService musicaService) {
        this.musicaService = musicaService;
    }

    @PostMapping
    public ResponseEntity<MusicaResponse> criarMusica(@RequestBody MusicaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(musicaService.criarMusica(request));
    }

    @GetMapping
    public ResponseEntity<List<MusicaResponse>> listarTodas() {
        return ResponseEntity.ok(musicaService.listarTodas());
    }
}