package com.DDD.AT.financeiro.transacao.modal;

import com.DDD.AT.financeiro.cartaoCredito.modal.CartaoCredito;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacoes")
@Getter
@NoArgsConstructor // Exigido pelo JPA
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cartao_id", nullable = false)
    private CartaoCredito cartaoCredito;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    public Transacao(Long usuarioId, CartaoCredito cartaoCredito, BigDecimal valor) {
        if (usuarioId == null) throw new IllegalArgumentException("A transação deve estar vinculada a um usuário válido.");
        if (cartaoCredito == null) throw new IllegalArgumentException("A transação precisa de um cartão de crédito associado.");
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("O valor da transação deve ser maior que zero.");

        this.usuarioId = usuarioId;
        this.cartaoCredito = cartaoCredito;
        this.valor = valor;
        this.dataHora = LocalDateTime.now();
    }
}