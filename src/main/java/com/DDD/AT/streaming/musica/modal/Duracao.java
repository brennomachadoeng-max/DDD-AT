package com.DDD.AT.streaming.musica.modal;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Duracao {

    @Column(name = "duracao_segundos", nullable = false)
    private Integer segundos;

    public Duracao(Integer segundos) {
        if (segundos == null || segundos <= 0) {
            throw new IllegalArgumentException("A duração deve ser maior que zero segundos.");
        }
        this.segundos = segundos;
    }

    public String formatarMinutos() {
        int minutos = segundos / 60;
        int restanteSegundos = segundos % 60;
        return String.format("%02d:%02d", minutos, restanteSegundos);
    }
}