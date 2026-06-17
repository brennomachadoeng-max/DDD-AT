package com.DDD.AT.financeiro.transacao.dto;

import lombok.Value;
import java.math.BigDecimal;

@Value
public class TransacaoRequest {
    Long cartaoId;
    BigDecimal valor;
}