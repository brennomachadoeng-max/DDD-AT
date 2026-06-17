package com.DDD.AT.financeiro.cartaoCredito.modal;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class NumeroCartao {

    @Column(name = "numero", nullable = false, length = 16)
    private String numeroCartao;

    public NumeroCartao(String numeroBruto) {
        if (numeroBruto == null) throw new IllegalArgumentException("Número do cartão não pode ser nulo.");
        String apenasNumeros = numeroBruto.replaceAll("\\D", "");
        if (apenasNumeros.length() != 16) throw new IllegalArgumentException("Número de cartão inválido. Deve conter exatamente 16 dígitos.");
        this.numeroCartao = apenasNumeros;
    }
}
