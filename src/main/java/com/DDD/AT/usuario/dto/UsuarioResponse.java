package com.DDD.AT.usuario.dto;
import lombok.Value;

import java.time.LocalDate;

@Value
public class UsuarioResponse {
    Long id;
    String nome;
    String email;
    LocalDate dataNascimento;
}
