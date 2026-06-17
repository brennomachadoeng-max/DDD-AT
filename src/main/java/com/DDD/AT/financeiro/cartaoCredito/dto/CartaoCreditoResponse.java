package com.DDD.AT.financeiro.cartaoCredito.dto;

import lombok.Value;

@Value
public class CartaoCreditoResponse {
    Long id;
    boolean ativo;
    Long usuarioId;
}