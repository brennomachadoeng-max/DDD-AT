package com.DDD.AT.financeiro.transacao.service;

import com.DDD.AT.financeiro.cartaoCredito.modal.CartaoCredito;
import com.DDD.AT.financeiro.cartaoCredito.service.CartaoCreditoService;
import com.DDD.AT.financeiro.transacao.dto.TransacaoRequest;
import com.DDD.AT.financeiro.transacao.dto.TransacaoResponse;
import com.DDD.AT.financeiro.transacao.modal.Transacao;
import com.DDD.AT.financeiro.transacao.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final CartaoCreditoService cartaoCreditoService;
    private final ValidadorTransacaoService validadorTransacaoService;

    public TransacaoService(TransacaoRepository transacaoRepository,
                            CartaoCreditoService cartaoCreditoService,
                            ValidadorTransacaoService validadorTransacaoService) {
        this.transacaoRepository = transacaoRepository;
        this.cartaoCreditoService = cartaoCreditoService;
        this.validadorTransacaoService = validadorTransacaoService;
    }

    public TransacaoResponse processarPagamento(TransacaoRequest request) {
        CartaoCredito cartao = cartaoCreditoService.selecionarPorId(request.getCartaoId());
        if (cartao == null) throw new IllegalArgumentException("Cartão de crédito não encontrado.");
        Transacao novaTransacao = transacaoRequestParaTransacao(request, cartao);
        validadorTransacaoService.verificar(novaTransacao);
        Transacao transacaoSalva = registrarTransacao(novaTransacao);
        return transacaoParaTransacaoResponse(transacaoSalva);
    }

    public Transacao registrarTransacao(Transacao transacao) {
        return transacaoRepository.save(transacao);
    }

    public Transacao transacaoRequestParaTransacao(TransacaoRequest request, CartaoCredito cartao) {
        return new Transacao(
                cartao.getUsuarioId(),
                cartao,
                request.getValor()
        );
    }

    public TransacaoResponse transacaoParaTransacaoResponse(Transacao transacao) {
        return new TransacaoResponse(
                transacao.getId(),
                transacao.getUsuarioId(),
                transacao.getCartaoCredito().getId(),
                transacao.getValor(),
                transacao.getDataHora()
        );
    }
}