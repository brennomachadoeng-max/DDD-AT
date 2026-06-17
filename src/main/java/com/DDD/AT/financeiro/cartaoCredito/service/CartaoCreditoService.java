package com.DDD.AT.financeiro.cartaoCredito.service;

import com.DDD.AT.financeiro.cartaoCredito.dto.CartaoCreditoRequest;
import com.DDD.AT.financeiro.cartaoCredito.dto.CartaoCreditoResponse;
import com.DDD.AT.financeiro.cartaoCredito.modal.CartaoCredito;
import com.DDD.AT.financeiro.cartaoCredito.repository.CartaoCreditoRepository;
import org.springframework.stereotype.Service;

@Service
public class CartaoCreditoService {

    private final CartaoCreditoRepository cartaoCreditoRepository;

    public CartaoCreditoService(CartaoCreditoRepository cartaoCreditoRepository) {
        this.cartaoCreditoRepository = cartaoCreditoRepository;
    }

    public CartaoCreditoResponse cadastrar(CartaoCreditoRequest request) {
        return cartaoCreditoParaCartaoCreditoResponse(cartaoCreditoRepository.save(cartaoCreditoRequestParaCartaoCredito(request)));
    }
    public CartaoCredito selecionarPorId(Long id) {
        return cartaoCreditoRepository.findById(id).orElse(null);
    }
    public CartaoCreditoResponse buscarPorId(Long id) {
        return cartaoCreditoRepository.findById(id).map(this::cartaoCreditoParaCartaoCreditoResponse).orElse(null);
    }
    public CartaoCreditoResponse ativar(Long id) {
        CartaoCredito cartaoCredito = selecionarPorId(id);
        cartaoCredito.activar();
        return cartaoCreditoParaCartaoCreditoResponse(cartaoCreditoRepository.save(cartaoCredito));
    }
    public CartaoCreditoResponse desativar(Long id) {
        CartaoCredito cartao = selecionarPorId(id);
        cartao.desativar();
        return cartaoCreditoParaCartaoCreditoResponse(cartaoCreditoRepository.save(cartao));
    }
    public CartaoCredito cartaoCreditoRequestParaCartaoCredito(CartaoCreditoRequest request) {
        return new CartaoCredito(
                request.getNumero(),
                request.getTitular(),
                request.getCvv(),
                request.getDataExpiracao(),
                request.getUsuarioId()
        );
    }
    public CartaoCreditoResponse cartaoCreditoParaCartaoCreditoResponse(CartaoCredito cartao) {
        return new CartaoCreditoResponse(
                cartao.getId(),
                cartao.isAtivo(),
                cartao.getUsuarioId()
        );
    }
}