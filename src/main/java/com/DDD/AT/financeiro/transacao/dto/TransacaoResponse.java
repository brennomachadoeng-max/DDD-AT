package com.DDD.AT.financeiro.transacao.dto;

import lombok.Value;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
public class TransacaoResponse {
    Long id;
    Long usuarioId;
    Long cartaoId;
    BigDecimal valor;
    LocalDateTime dataHora;
}