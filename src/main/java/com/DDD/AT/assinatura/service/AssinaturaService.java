package com.DDD.AT.assinatura.service;

import com.DDD.AT.assinatura.dto.AssinaturaRequest;
import com.DDD.AT.assinatura.dto.AssinaturaResponse;
import com.DDD.AT.assinatura.modal.Assinatura;
import com.DDD.AT.assinatura.plano.modal.Plano;
import com.DDD.AT.assinatura.plano.service.PlanoService;
import com.DDD.AT.assinatura.repository.AssinaturaRepository;
import com.DDD.AT.financeiro.cartaoCredito.modal.CartaoCredito;
import com.DDD.AT.financeiro.cartaoCredito.repository.CartaoCreditoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssinaturaService {

    private final AssinaturaRepository assinaturaRepository;
    private final PlanoService planoService;
    private final CartaoCreditoRepository cartaoCreditoRepository;

    public AssinaturaService(AssinaturaRepository assinaturaRepository, PlanoService planoService, CartaoCreditoRepository cartaoCreditoRepository) {
        this.assinaturaRepository = assinaturaRepository;
        this.planoService = planoService;
        this.cartaoCreditoRepository = cartaoCreditoRepository;
    }


    public AssinaturaResponse criarAssinatura(AssinaturaRequest request) {
        validarPossuiCartaoValido(request.getUsuarioId());
        validarAssinaturaAtivaExistente(request.getUsuarioId());
        Plano plano = planoService.selecionarPorId(request.getPlanoId());
        Assinatura novaAssinatura = assinaturaRequestParaAssinatura(request, plano);
        Assinatura assinaturaSalva = registrarAssinatura(novaAssinatura);
        return assinaturaParaAssinaturaResponse(assinaturaSalva);
    }


    private void validarPossuiCartaoValido(Long usuarioId) {
        boolean temCartaoValido = cartaoCreditoRepository.findByUsuarioId(usuarioId).stream().anyMatch(CartaoCredito::isValidoParaTransacao);
        if (!temCartaoValido) throw new IllegalStateException("operação-rejeitada: O usuário precisa cadastrar um cartão de crédito válido e ativo antes de contratar um plano.");
    }


    private void validarAssinaturaAtivaExistente(Long usuarioId) {
        assinaturaRepository.findByUsuarioIdAndAtivaTrue(usuarioId).ifPresent(assinatura -> {throw new IllegalStateException("O usuário já possui um plano ativo. Não é possível assinar outro plano simultaneamente.");});
    }

    public Assinatura registrarAssinatura(Assinatura assinatura) {
        return assinaturaRepository.save(assinatura);
    }

    public Assinatura selecionarPorId(Long id) {
        return assinaturaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Assinatura não encontrada com o ID fornecido."));
    }

    public List<AssinaturaResponse> listarPorUsuario(Long usuarioId) {
        return assinaturaRepository.findByUsuarioId(usuarioId).stream().map(this::assinaturaParaAssinaturaResponse).collect(Collectors.toList());
    }

    public Assinatura assinaturaRequestParaAssinatura(AssinaturaRequest request, Plano plano) {
        return new Assinatura(
                request.getUsuarioId(),
                plano
        );
    }

    public AssinaturaResponse assinaturaParaAssinaturaResponse(Assinatura assinatura) {
        return new AssinaturaResponse(
                assinatura.getId(),
                assinatura.getUsuarioId(),
                assinatura.getPlano().getId(),
                assinatura.getDataInicio(),
                assinatura.getDataExpiracao(),
                assinatura.isAtiva()
        );
    }
}