package com.DDD.AT.assinatura.plano.service;

import com.DDD.AT.assinatura.plano.dto.PlanoRequest;
import com.DDD.AT.assinatura.plano.dto.PlanoResponse;
import com.DDD.AT.assinatura.plano.modal.Plano;
import com.DDD.AT.assinatura.plano.repository.PlanoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanoService {

    private final PlanoRepository planoRepository;

    public PlanoService(PlanoRepository planoRepository) {
        this.planoRepository = planoRepository;
    }

    public PlanoResponse criarPlano(PlanoRequest request) {
        return planoParaPlanoResponse(registrarPlano(planoRequestParaPlano(request)));
    }

    public Plano registrarPlano(Plano plano) {
        return planoRepository.save(plano);
    }

    public Plano selecionarPorId(Long id) {
        return planoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Plano não encontrado com o ID fornecido."));
    }

    public List<PlanoResponse> listarTodos() {
        return planoRepository.findAll().stream().map(this::planoParaPlanoResponse).collect(Collectors.toList());
    }

    public Plano planoRequestParaPlano(PlanoRequest request) {
        return new Plano(
                request.getNome(),
                request.getDescricao(),
                request.getValor()
        );
    }

    public PlanoResponse planoParaPlanoResponse(Plano plano) {
        return new PlanoResponse(
                plano.getId(),
                plano.getNome(),
                plano.getDescricao(),
                plano.getValor(),
                plano.isAtivo()
        );
    }
}
