package com.DDD.AT.streaming.playlist.service;

import com.DDD.AT.streaming.musica.dto.MusicaResponse;
import com.DDD.AT.streaming.musica.service.MusicaService;
import com.DDD.AT.streaming.playlist.dto.PlaylistRequest;
import com.DDD.AT.streaming.playlist.dto.PlaylistResponse;
import com.DDD.AT.streaming.playlist.modal.Playlist;
import com.DDD.AT.streaming.playlist.repository.PlaylistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final MusicaService musicaService;

    public PlaylistService(PlaylistRepository playlistRepository, MusicaService musicaService) {
        this.playlistRepository = playlistRepository;
        this.musicaService = musicaService;
    }

    public PlaylistResponse criarPlaylist(PlaylistRequest request) {
        return playlistParaPlaylistResponse(registrarPlaylist(playlistRequestParaPlaylist(request)));
    }

    public PlaylistResponse inicializarFavoritos(Long usuarioId) {
        return playlistParaPlaylistResponse(registrarPlaylist(Playlist.criarPlaylistFavoritos(usuarioId)));
    }

    public Playlist registrarPlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    public PlaylistResponse adicionarMusicaNaPlaylist(Long playlistId, Long musicaId) {
        Playlist playlist = encontrarMusicaPlaylist(playlistId);
        playlist.adicionarMusica(musicaService.selecionarPorId(musicaId));
        return playlistParaPlaylistResponse(registrarPlaylist(playlist));
    }

    public PlaylistResponse removerMusicaDaPlaylist(Long playlistId, Long musicaId) {
        Playlist playlist = encontrarMusicaPlaylist(playlistId);
        playlist.removerMusica(musicaService.selecionarPorId(musicaId));
        return playlistParaPlaylistResponse(registrarPlaylist(playlist));
    }

    public Playlist encontrarMusicaPlaylist(Long playlistId) {
        return playlistRepository.encontrarPorIdComMusicas(playlistId).orElseThrow(() -> new IllegalArgumentException("Playlist não encontrada com o ID fornecido."));
    }

    public Playlist selecionarPorId(Long id) {
        return playlistRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Playlist não encontrada com o ID fornecido."));
    }

    public PlaylistResponse playlistParaPlaylistResponse(Playlist playlist) {
        List<MusicaResponse> musicasDto = playlist.getMusicas().stream()
                .map(musica -> new MusicaResponse(
                        musica.getId(),
                        musica.getTitulo(),
                        musica.getArtista(),
                        musica.getDuracao()
                ))
                .collect(Collectors.toList());

        return new PlaylistResponse(
                playlist.getId(),
                playlist.getNome(),
                playlist.getUsuarioId(),
                playlist.isFavorito(),
                musicasDto
        );
    }

    public Playlist playlistRequestParaPlaylist(PlaylistRequest request) {
        return new Playlist(
                request.getNome(),
                request.getUsuarioId()
        );
    }
}