package com.DDD.AT.usuario.modal;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Embeddable
@Getter
@NoArgsConstructor
public class DataNascimento {

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate data;

    public DataNascimento(LocalDate dataNascimento) {
        if (dataNascimento == null) throw new IllegalArgumentException("Data de nascimento é obrigatória.");
        if (dataNascimento.isAfter(LocalDate.now())) throw new IllegalArgumentException("Data de nascimento não pode ser uma data futura.");
        int idade = Period.between(dataNascimento, LocalDate.now()).getYears();
        if (idade < 13) throw new IllegalArgumentException("Usuário deve ter pelo menos 13 anos para criar uma conta.");
        this.data = dataNascimento;
    }
}