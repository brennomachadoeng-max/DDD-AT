package com.DDD.AT.usuario.modal;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Senha {

    @Column(name = "senha", nullable = false)
    private String valor;

    public Senha(String senhaBruta) {
        if (senhaBruta == null || senhaBruta.isBlank()) throw new IllegalArgumentException("A senha é obrigatória.");
        if (senhaBruta.length() < 6) throw new IllegalArgumentException("A senha deve conter pelo menos 6 caracteres.");
        this.valor = senhaBruta;
    }
}