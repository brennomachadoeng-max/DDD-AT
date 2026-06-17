package com.DDD.AT.assinatura.plano.dto;

import lombok.Value;
import java.math.BigDecimal;

@Value
public class PlanoResponse {
    Long id;
    String nome;
    String descricao;
    BigDecimal valor;
    boolean ativo;
}
