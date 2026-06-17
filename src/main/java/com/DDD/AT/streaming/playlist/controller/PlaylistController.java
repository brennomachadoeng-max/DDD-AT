package com.DDD.AT.streaming.playlist.controller;

import com.DDD.AT.streaming.playlist.dto.PlaylistRequest;
import com.DDD.AT.streaming.playlist.dto.PlaylistResponse;
import com.DDD.AT.streaming.playlist.service.PlaylistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/streaming/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping
    public ResponseEntity<PlaylistResponse> criarPlaylist(@RequestBody PlaylistRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playlistService.criarPlaylist(request));
    }

    @PostMapping("/usuarios/{usuarioId}/favoritos")
    public ResponseEntity<PlaylistResponse> inicializarFavoritos(@PathVariable Long usuarioId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playlistService.inicializarFavoritos(usuarioId));
    }

    @PostMapping("/{playlistId}/musicas/{musicaId}")
    public ResponseEntity<PlaylistResponse> adicionarMusica(@PathVariable Long playlistId, @PathVariable Long musicaId) {
        return ResponseEntity.ok(playlistService.adicionarMusicaNaPlaylist(playlistId, musicaId));
    }

    @DeleteMapping("/{playlistId}/musicas/{musicaId}")
    public ResponseEntity<PlaylistResponse> removerMusica(@PathVariable Long playlistId, @PathVariable Long musicaId) {
        return ResponseEntity.ok(playlistService.removerMusicaDaPlaylist(playlistId, musicaId));
    }
}