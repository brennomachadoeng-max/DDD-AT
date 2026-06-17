package com.DDD.AT.streaming.playlist.dto;

import lombok.Value;

@Value
public class PlaylistRequest {
    String nome;
    Long usuarioId;
}