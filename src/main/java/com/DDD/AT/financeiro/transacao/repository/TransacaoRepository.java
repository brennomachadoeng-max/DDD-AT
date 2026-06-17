package com.DDD.AT.financeiro.transacao.repository;

import com.DDD.AT.financeiro.transacao.modal.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findByCartaoCreditoIdAndDataHoraAfter(Long cartaoId, LocalDateTime dataHora);
}
