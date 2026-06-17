package com.DDD.AT.usuario.dto;
import lombok.Value;

import java.time.LocalDate;

@Value
public class UsuarioRequest {
    String nome;
    String email;
    String senha;
    LocalDate dataNascimento;
    String cpf;
}
