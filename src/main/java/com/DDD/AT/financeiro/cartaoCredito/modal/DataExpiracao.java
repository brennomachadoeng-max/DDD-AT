package com.DDD.AT.financeiro.cartaoCredito.modal;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Embeddable
@Getter
@NoArgsConstructor
public class DataExpiracao {

    @Column(name = "data_expiracao", nullable = false)
    private LocalDate data;

    public DataExpiracao(LocalDate dataExpiracao) {
        if (dataExpiracao == null) throw new IllegalArgumentException("A data de expiração é obrigatória.");
        if (dataExpiracao.isBefore(LocalDate.now())) throw new IllegalArgumentException("O cartão de crédito está expirado.");
        this.data = dataExpiracao;
    }
}