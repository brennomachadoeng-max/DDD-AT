package com.DDD.AT.streaming.musica.dto;

import lombok.Value;

@Value
public class MusicaResponse {
    Long id;
    String titulo;
    String artista;
    Integer duracaoSegundos;
}