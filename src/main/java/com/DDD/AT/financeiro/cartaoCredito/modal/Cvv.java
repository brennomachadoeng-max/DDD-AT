package com.DDD.AT.financeiro.cartaoCredito.modal;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Cvv {

    @Column(name = "cvv", nullable = false, length = 4)
    private String cvv;

    public Cvv(String cvvBruto) {
        if (cvvBruto == null || cvvBruto.trim().length() < 3 || cvvBruto.trim().length() > 4) throw new IllegalArgumentException("CVV inválido. Deve conter 3 ou 4 dígitos.");
        String apenasNumeros = cvvBruto.replaceAll("\\D", "");
        if (apenasNumeros.length() < 3) throw new IllegalArgumentException("O CVV deve ser composto apenas por números.");
        this.cvv = apenasNumeros;
    }
}
