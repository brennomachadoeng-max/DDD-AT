package com.DDD.AT.assinatura.service;

import com.DDD.AT.assinatura.dto.AssinaturaRequest;
import com.DDD.AT.assinatura.dto.AssinaturaResponse;
import com.DDD.AT.assinatura.modal.Assinatura;
import com.DDD.AT.assinatura.plano.modal.Plano;
import com.DDD.AT.assinatura.plano.service.PlanoService;
import com.DDD.AT.assinatura.repository.AssinaturaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssinaturaService {

    private final AssinaturaRepository assinaturaRepository;
    private final PlanoService planoService;

    public AssinaturaService(AssinaturaRepository assinaturaRepository, PlanoService planoService) {
        this.assinaturaRepository = assinaturaRepository;
        this.planoService = planoService;
    }

    public AssinaturaResponse criarAssinatura(AssinaturaRequest request) {
        return assinaturaParaAssinaturaResponse(registrarAssinatura(assinaturaRequestParaAssinatura(request, planoService.selecionarPorId(request.getPlanoId()))));
    }
    public Assinatura registrarAssinatura(Assinatura assinatura) {
        return assinaturaRepository.save(assinatura);
    }
    public Assinatura selecionarPorId(Long id) {
        return assinaturaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Assinatura não encontrada com o ID fornecido."));
    }
    public List<AssinaturaResponse> listarPorUsuario(Long usuarioId) {
        return assinaturaRepository.findAll().stream().filter(a -> a.getUsuarioId().equals(usuarioId)).map(this::assinaturaParaAssinaturaResponse).collect(Collectors.toList());
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