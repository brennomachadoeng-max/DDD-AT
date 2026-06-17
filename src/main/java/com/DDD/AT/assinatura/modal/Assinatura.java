package com.DDD.AT.assinatura.modal;

import com.DDD.AT.assinatura.plano.modal.Plano;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "assinaturas")
@Getter
@NoArgsConstructor // Exigido pelo JPA
public class Assinatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plano_id", nullable = false)
    private Plano plano;

    @Column(name = "data_inicio", nullable = false)
    private LocalDateTime dataInicio;

    @Column(name = "data_expiracao", nullable = false)
    private LocalDateTime dataExpiracao;

    @Column(nullable = false)
    private boolean ativa;

    public Assinatura(Long usuarioId, Plano plano) {
        if (usuarioId == null) {
            throw new IllegalArgumentException("A assinatura deve pertencer a um usuário válido.");
        }
        if (plano == null) {
            throw new IllegalArgumentException("A assinatura deve estar vinculada a um plano válido.");
        }
        if (!plano.isAtivo()) {
            throw new IllegalStateException("Não é possível assinar um plano que está desativado.");
        }

        this.usuarioId = usuarioId;
        this.plano = plano;
        this.dataInicio = LocalDateTime.now();
        this.dataExpiracao = this.dataInicio.plusMonths(1);
        this.ativa = true;
    }

    public boolean verificarSeExpirou() {
        if (LocalDateTime.now().isAfter(this.dataExpiracao)) {
            this.ativa = false;
            return true;
        }
        return false;
    }
    public void cancelar() {
        this.ativa = false;
    }
    public void renovar() {
        this.dataExpiracao = this.dataExpiracao.plusMonths(1);
        this.ativa = true;
    }
}