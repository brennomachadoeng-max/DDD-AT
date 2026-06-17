package com.DDD.AT.usuario.modal;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Email {

    @Column(name = "email", nullable = false, unique = true)
    private String valor;

    public Email(String emailBruto) {
        if (emailBruto == null || emailBruto.isBlank()) throw new IllegalArgumentException("Email é obrigatório.");
        if (!emailBruto.contains("@") || !emailBruto.contains(".")) throw new IllegalArgumentException("Formato de email inválido.");
        this.valor = emailBruto.trim().toLowerCase();
    }
}