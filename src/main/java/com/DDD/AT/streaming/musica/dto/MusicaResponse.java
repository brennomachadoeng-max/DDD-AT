package com.DDD.AT.streaming.musica.dto;

import com.DDD.AT.streaming.musica.modal.Duracao;
import lombok.Value;

@Value
public class MusicaResponse {
    Long id;
    String titulo;
    String artista;
    Duracao duracaoSegundos;
}