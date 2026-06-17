package com.DDD.AT.assinatura.plano.modal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "planos")
@Getter
@NoArgsConstructor
public class Plano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(nullable = false)
    private boolean ativo;

    public Plano(String nome, String description, BigDecimal valor) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do plano é obrigatório.");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("A descrição do plano é obrigatória.");
        }
        if (valor == null || valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor do plano não pode ser negativo.");
        }

        this.nome = nome.trim();
        this.descricao = description.trim();
        this.valor = valor;
        this.ativo = true; // Todo plano nasce ativo por padrão
    }

    public void desativar() {
        this.ativo = false;
    }

    public void ativar() {
        this.ativo = true;
    }

    public void atualizarValor(BigDecimal novoValor) {
        if (novoValor == null || novoValor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O novo valor do plano não pode ser negativo.");
        }
        this.valor = novoValor;
    }
}
