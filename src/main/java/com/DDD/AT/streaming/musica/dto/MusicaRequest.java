package com.DDD.AT.streaming.musica.dto;

import lombok.Value;

@Value
public class MusicaRequest {
    String titulo;
    String artista;
    Integer duracaoSegundos;
}