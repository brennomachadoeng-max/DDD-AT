package com.DDD.AT.financeiro.transacao.service;

import com.DDD.AT.financeiro.transacao.modal.Transacao;
import com.DDD.AT.financeiro.transacao.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ValidadorTransacaoService {

    private final TransacaoRepository transacaoRepository;

    public ValidadorTransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public void verificar(Transacao novaTransacao) {
        if (!novaTransacao.getCartaoCredito().isAtivo()) throw new IllegalStateException("cartão não ativo");
        LocalDateTime limiteDoisMinutos = novaTransacao.getDataHora().minusMinutes(2);
        List<Transacao> transacoesRecentes = transacaoRepository.findByCartaoCreditoIdAndDataHoraAfter(novaTransacao.getCartaoCredito().getId(), limiteDoisMinutos);
        verificarAltaFrequencia(transacoesRecentes);
        verificarTransacaoDuplicada(transacoesRecentes, novaTransacao);
    }

    private void verificarAltaFrequencia(List<Transacao> transacoesRecentes) {
        if (transacoesRecentes.size() >= 3) throw new IllegalStateException("alta-frequência-pequeno-intervalo");
    }

    private void verificarTransacaoDuplicada(List<Transacao> transacoesRecentes, Transacao novaTransacao) {
        long quantidadeDuplicadas = transacoesRecentes.stream().filter(t -> t.getValor().compareTo(novaTransacao.getValor()) == 0).count();
        if (quantidadeDuplicadas >= 2) throw new IllegalStateException("transação duplicada");
    }
}