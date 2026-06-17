package com.DDD.AT.usuario.modal;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Cpf {

    @Column(name = "cpf", nullable = false, unique = true)
    private String valor;

    public Cpf(String cpfBruto) {
        if (cpfBruto == null || cpfBruto.isBlank()) throw new IllegalArgumentException("CPF é obrigatório.");
        String apenasNumeros = cpfBruto.replaceAll("\\D", "");
        if (apenasNumeros.length() != 11) throw new IllegalArgumentException("CPF deve conter exatamente 11 dígitos.");
        this.valor = apenasNumeros;
    }
}