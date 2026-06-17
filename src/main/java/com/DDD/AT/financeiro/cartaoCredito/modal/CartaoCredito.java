package com.DDD.AT.financeiro.cartaoCredito.modal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "cartoes_credito")
@Getter
@NoArgsConstructor
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

    @OneToMany(mappedBy = "cartoes_credito", cascade = CascadeType.ALL)
    private List<CartaoCredito> cartaoCreditos;


    public CartaoCredito(String numero, String titular, String cvv, LocalDate dataExpiracao, Long usuarioId) {
        this.numero = new NumeroCartao(numero);
        this.titular = titular.toUpperCase().trim();
        this.cvv = new Cvv(cvv);
        this.dataExpiracao = new DataExpiracao(dataExpiracao);
        this.ativo = false;
        this.usuarioId = usuarioId;
    }

    public void activar() {
        this.ativo = true;
    }
    public void desativar() {
        this.ativo = false;
    }
}