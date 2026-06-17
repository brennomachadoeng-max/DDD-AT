package com.DDD.AT.assinatura.dto;

import lombok.Value;
import java.time.LocalDateTime;

@Value
public class AssinaturaResponse {
    Long id;
    Long usuarioId;
    Long planoId;
    LocalDateTime dataInicio;
    LocalDateTime dataExpiracao;
    boolean ativa;
}