package com.DDD.AT.assinatura.plano.dto;

import lombok.Value;
import java.math.BigDecimal;

@Value
public class PlanoRequest {
    String nome;
    String descricao;
    BigDecimal valor;
}
