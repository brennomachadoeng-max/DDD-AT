package com.DDD.AT.financeiro.cartaoCredito.dto;

import lombok.Value;
import java.time.LocalDate;

@Value
public class CartaoCreditoRequest {
    String numero;
    String titular;
    String cvv;
    LocalDate dataExpiracao;
    Long usuarioId;
}