package com.DDD.AT.streaming.musica.service;

import com.DDD.AT.streaming.musica.dto.MusicaRequest;
import com.DDD.AT.streaming.musica.dto.MusicaResponse;
import com.DDD.AT.streaming.musica.modal.Musica;
import com.DDD.AT.streaming.musica.repository.MusicaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicaService {

    private final MusicaRepository musicaRepository;

    public MusicaService(MusicaRepository musicaRepository) {
        this.musicaRepository = musicaRepository;
    }

    public MusicaResponse criarMusica(MusicaRequest request) {
        return musicaParaMusicaResponse(registrarMusica(musicaRequestParaMusica(request)));
    }

    public Musica registrarMusica(Musica musica) {
        return musicaRepository.save(musica);
    }

    public Musica selecionarPorId(Long id) {
        return musicaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Música não encontrada com o ID fornecido."));
    }

    public List<MusicaResponse> listarTodas() {
        return musicaRepository.findAll().stream().map(this::musicaParaMusicaResponse).collect(Collectors.toList());
    }

    public Musica musicaRequestParaMusica(MusicaRequest request) {
        return new Musica(
                request.getTitulo(),
                request.getArtista(),
                request.getDuracaoSegundos()
        );
    }

    public MusicaResponse musicaParaMusicaResponse(Musica musica) {
        return new MusicaResponse(
                musica.getId(),
                musica.getTitulo(),
                musica.getArtista(),
                musica.getDuracaoSegundos()
        );
    }
}