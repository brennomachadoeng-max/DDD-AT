package com.DDD.AT.streaming.playlist.dto;

import com.DDD.AT.streaming.musica.dto.MusicaResponse;
import lombok.Value;
import java.util.List;

@Value
public class PlaylistResponse {
    Long id;
    String nome;
    Long usuarioId;
    boolean favorito;
    List<MusicaResponse> musicas;
}