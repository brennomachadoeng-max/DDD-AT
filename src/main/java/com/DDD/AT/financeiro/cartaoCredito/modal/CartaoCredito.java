package com.DDD.AT.financeiro.cartaoCredito.modal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "cartoes_credito")
@Getter
@NoArgsConstructor // Exigido pelo JPA
public class CartaoCredito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private NumeroCartao numero;

    @Column(nullable = false)
    private String titular;

    @Embedded
    private Cvv cvv;

    @Embedded
    private DataExpiracao dataExpiracao;

    @Column(nullable = false)
    private boolean ativo;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    /**
     * Construtor de Domínio Rico - Valida e instancia um cartão válido
     */
    public CartaoCredito(String numero, String titular, String cvv, LocalDate dataExpiracao, Long usuarioId) {
        if (titular == null || titular.isBlank()) {
            throw new IllegalArgumentException("O titular do cartão é obrigatório.");
        }
        if (usuarioId == null) {
            throw new IllegalArgumentException("O cartão deve estar associado a um usuário válido.");
        }

        this.numero = new NumeroCartao(numero);
        this.titular = titular.toUpperCase().trim();
        this.cvv = new Cvv(cvv);
        this.dataExpiracao = new DataExpiracao(dataExpiracao);

        // Regra de negócio: O cartão nasce ativo se não estiver expirado
        this.ativo = !isExpirado();
        this.usuarioId = usuarioId;
    }

    /**
     * Intention Revealing Method: Revela se o cartão passou da data de validade
     */
    public boolean isExpirado() {
        if (this.dataExpiracao == null || this.dataExpiracao.getData() == null) {
            return true;
        }
        return LocalDate.now().isAfter(this.dataExpiracao.getData());
    }

    /**
     * Regra Antifraude: Um cartão só é verdadeiramente VÁLIDO se estiver ativo e não expirado
     */
    public boolean isValidoParaTransacao() {
        return this.ativo && !isExpirado();
    }

    public void ativar() {
        if (isExpirado()) {
            throw new IllegalStateException("Não é possível ativar um cartão vencido.");
        }
        this.ativo = true;
    }

    public void desativar() {
        this.ativo = false;
    }
}